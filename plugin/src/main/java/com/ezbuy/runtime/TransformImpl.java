package com.ezbuy.runtime;

import com.android.build.api.transform.Context;
import com.android.build.api.transform.DirectoryInput;
import com.android.build.api.transform.Format;
import com.android.build.api.transform.JarInput;
import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformOutputProvider;
import com.android.build.gradle.internal.pipeline.TransformManager;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Set;

import static org.objectweb.asm.ClassReader.EXPAND_FRAMES;

/**
 * author : yutianran
 * time   : 2019/01/15
 * desc   :
 * version: 1.0
 */
public class TransformImpl extends Transform {

    @Override
    public String getName() {
        return "PluginImpl";
    }

    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS;
    }

    @Override
    public Set<QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    @Override
    public boolean isIncremental() {
        return false;
    }

    @Override
    public void transform(Context context, Collection<TransformInput> inputs, Collection<TransformInput> referencedInputs, TransformOutputProvider outputProvider, boolean isIncremental) throws IOException, TransformException, InterruptedException {
        System.out.println("//===============asm visit start===============//");
        //遍历inputs里的TransformInput
        for (TransformInput input : inputs) {
            for (DirectoryInput directoryInput : input.getDirectoryInputs()) {
                if (directoryInput.getFile().isDirectory()) {
                    for (File file : directoryInput.getFile().listFiles()) {
                        String name = file.getName();
                        if (name.endsWith(".class") && !"R.class".equals(name) && !"BuildConfig.class".equals(name)) {
                            ClassReader classReader = new ClassReader(Files.readAllBytes(file.toPath()));
                            ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
                            ClassVisitor cv = new CostMethodClassVisitor(classWriter);
                            classReader.accept(cv, EXPAND_FRAMES);
                            byte[] code = classWriter.toByteArray();
                            FileOutputStream fos = new FileOutputStream(file.getParentFile().getAbsolutePath() + File.separator + name);
                            fos.write(code);
                            fos.close();
                        }
                        System.out.println("//PluginImpl find file:" + file.getAbsolutePath());
                    }
                }
            }
            for (JarInput jarInput : input.getJarInputs()) {
                String jarName = jarInput.getName();
                String md5Name = DigestUtils.md5Hex(jarInput.getFile().getAbsolutePath());
                if (jarName.endsWith(".jar")) {
                    jarName = jarName.substring(0, jarName.length() - 4);
                }
                System.out.println("//PluginImpl find Jar:" + jarInput.getFile().getAbsolutePath());
                //TODO jar
                File dest = outputProvider.getContentLocation(jarName + md5Name, jarInput.getContentTypes(), jarInput.getScopes(), Format.JAR);
                FileUtils.copyFile(jarInput.getFile(), dest);
            }
        }
        System.out.println("//===============asm visit end===============//");
    }
}
