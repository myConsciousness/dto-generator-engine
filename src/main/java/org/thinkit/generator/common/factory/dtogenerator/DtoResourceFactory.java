/*
 * Copyright 2020 Kato Shinya.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.thinkit.generator.common.factory.dtogenerator;

import org.thinkit.generator.common.factory.resource.ClassDescription;
import org.thinkit.generator.common.factory.resource.Constructor;
import org.thinkit.generator.common.factory.resource.ConstructorProcess;
import org.thinkit.generator.common.factory.resource.Copyright;
import org.thinkit.generator.common.factory.resource.Description;
import org.thinkit.generator.common.factory.resource.FieldDefinition;
import org.thinkit.generator.common.factory.resource.FunctionDescription;
import org.thinkit.generator.common.factory.resource.FunctionParamAnnotation;
import org.thinkit.generator.common.factory.resource.Parameter;
import org.thinkit.generator.common.factory.resource.Resource;
import org.thinkit.generator.common.factory.resource.ResourceFactory;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * {#link ResourceFactory} を実装する具象ファクトリクラスです。
 * <p>
 * 以下のメソッドを使用することで各リソースの部品オブジェクトを取得することができます。<br>
 * {@link #createCopyright(String, String, String, String, String)} <br>
 * {@link #createClassDescription(String, String, String)} <br>
 * {@link #createDescription(String)} <br>
 * {@link #createFieldDefinition(String, String, String)} <br>
 * {@link #createFunctionDescription(String)} <br>
 * {@link #createFunctionParamAnnotation(String, String)} <br>
 * {@link #createConstructor(String, FunctionDescription)} <br>
 * {@link #createParameter(String, String)} <br>
 * {@link #createConstructorProcess(String)} <br>
 * {@link #createConstructorProcess(String, String)} <br>
 * {@link #createResource(Copyright, String, ClassDescription, String)} <br>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class DtoResourceFactory extends ResourceFactory {

    /**
     * デフォルトコンストラクタ
     */
    private DtoResourceFactory() {
    }

    /**
     * {@link DtoResourceFactory} のシングルトンインスタンスを返却します。
     *
     * @return {@link DtoResourceFactory} のシングルトンインスタンス
     */
    public static ResourceFactory getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * {@link DtoResourceFactory} のシングルトンインスタンスを保持するインナークラスです。<br>
     * {@link DtoResourceFactory} シングルトンインスタンスは初回参照時にメモリに読み込まれます。
     */
    private static class InstanceHolder {

        /**
         * シングルトンインスタンス
         */
        private static final ResourceFactory INSTANCE = new DtoResourceFactory();
    }

    @Override
    public Copyright createCopyright(String projectName, String fileName, String encoding, String creator,
            String creationDate) {
        return new DtoCopyright(projectName, fileName, encoding, creator, creationDate);
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
    public ConstructorProcess createConstructorProcess(String variableName) {
        return new DtoConstructorProcess(variableName);
    }

    @Override
    public ConstructorProcess createConstructorProcess(String variableName, String getterName) {
        return new DtoConstructorProcess(variableName, getterName);
    }

    @Override
    public Resource createResource(Copyright copyright, String packageName, ClassDescription classDescription,
            String resourceName) {
        return new DtoResource(copyright, packageName, classDescription, resourceName);
    }
}