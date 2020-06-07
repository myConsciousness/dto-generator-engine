/**
 * Project Name : Generator<br>
 * File Name : DtoResourceFactory.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/06<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.factory.dtofactory;

import org.thinkit.generator.rule.factory.resource.ClassDescription;
import org.thinkit.generator.rule.factory.resource.Constructor;
import org.thinkit.generator.rule.factory.resource.Copyright;
import org.thinkit.generator.rule.factory.resource.Description;
import org.thinkit.generator.rule.factory.resource.Field;
import org.thinkit.generator.rule.factory.resource.FieldDefinition;
import org.thinkit.generator.rule.factory.resource.FunctionDescription;
import org.thinkit.generator.rule.factory.resource.FunctionParamAnnotation;
import org.thinkit.generator.rule.factory.resource.Parameter;
import org.thinkit.generator.rule.factory.resource.Process;
import org.thinkit.generator.rule.factory.resource.Resource;
import org.thinkit.generator.rule.factory.resource.ResourceFactory;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * {#link ResourceFactory}を実装する具象ファクトリクラスです。<br>
 * 以下のメソッドを使用することで各リソースの部品オブジェクトを取得することができます。<br>
 * {@link #createCopyright(String, String, String, String, String)} <br>
 * {@link #createClassDescription(String, String, String)} <br>
 * {@link #createDescription(String)} <br>
 * {@link #createField()} <br>
 * {@link #createFieldDefinition(String, String, String)} <br>
 * {@link #createFunctionDescription(String)} <br>
 * {@link #createFunctionParamAnnotation(String, String)} <br>
 * {@link #createConstructor(String, FunctionDescription)} <br>
 * {@link #createParameter(String, String)} <br>
 * {@link #createProcess(String)} <br>
 * {@link #createResource(Copyright, String, ClassDescription, String, Field)}
 * <br>
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class DtoResourceFactory extends ResourceFactory {

    /**
     * {@link DtoResourceFactory}のシングルトンインスタンスを保持するインナークラスです。<br>
     * {@link DtoResourceFactory}シングルトンインスタンスは初回参照時にメモリに読み込まれます。
     */
    private static class InstanceHolder {

        /**
         * シングルトンインスタンス
         */
        private static final ResourceFactory INSTANCE = new DtoResourceFactory();
    }

    /**
     * デフォルトコンストラクタ
     */
    private DtoResourceFactory() {
    }

    /**
     * {@link DtoResourceFactory}のシングルトンインスタンスを返却します。
     * 
     * @return {@link DtoResourceFactory}のシングルトンインスタンス
     */
    public static ResourceFactory getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public Copyright createCopyright(String projectName, String fileName, String encoding, String creationDate,
            String creator) {
        return new DtoCopyright(projectName, fileName, encoding, creationDate, creator);
    }

    @Override
    public ClassDescription createClassDescription(String description, String creator, String version) {
        return new DtoClassDescription(description, creator, version);
    }

    @Override
    public Description createDescription(String description) {
        return new DtoDescription(description);
    }

    @Override
    public Field createField() {
        return new DtoField();
    }

    @Override
    public FieldDefinition createFieldDefinition(String dataType, String variableName, String initialValue) {
        return new DtoFieldDefinition(dataType, variableName, initialValue);
    }

    @Override
    public FunctionDescription createFunctionDescription(String description) {
        return new DtoMethodDescription(description);
    }

    @Override
    public FunctionParamAnnotation createFunctionParamAnnotation(String variableName, String description) {
        return new DtoMethodParamAnnotation(variableName, description);
    }

    @Override
    public Constructor createConstructor(String functionName, FunctionDescription functionDescription) {
        return new DtoConstructor(functionName, functionDescription);
    }

    @Override
    public Parameter createParameter(String dataType, String variableName) {
        return new DtoParameter(dataType, variableName);
    }

    @Override
    public Process createProcess(String variableName) {
        return new DtoProcess(variableName);
    }

    @Override
    public Resource createResource(Copyright copyright, String packageName, ClassDescription classDescription,
            String resourceName, Field field) {
        return new DtoResource(copyright, packageName, classDescription, resourceName, field);
    }
}