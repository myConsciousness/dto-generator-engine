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

import org.thinkit.generator.common.catalog.Annotation;
import org.thinkit.generator.common.factory.resource.ClassDescription;
import org.thinkit.generator.common.factory.resource.Constructor;
import org.thinkit.generator.common.factory.resource.ConstructorProcess;
import org.thinkit.generator.common.factory.resource.Copyright;
import org.thinkit.generator.common.factory.resource.Description;
import org.thinkit.generator.common.factory.resource.DescriptionTag;
import org.thinkit.generator.common.factory.resource.EnumDefinition;
import org.thinkit.generator.common.factory.resource.Enumeration;
import org.thinkit.generator.common.factory.resource.Field;
import org.thinkit.generator.common.factory.resource.FieldDefinition;
import org.thinkit.generator.common.factory.resource.FunctionDescription;
import org.thinkit.generator.common.factory.resource.Generics;
import org.thinkit.generator.common.factory.resource.Inheritance;
import org.thinkit.generator.common.factory.resource.Interface;
import org.thinkit.generator.common.factory.resource.MethodProcess;
import org.thinkit.generator.common.factory.resource.Parameter;
import org.thinkit.generator.common.factory.resource.Resource;
import org.thinkit.generator.common.factory.resource.ResourceFactory;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * {#link ResourceFactory} を実装する具象ファクトリクラスです。
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
    @Deprecated
    public Copyright createCopyright(@NonNull String creator) {
        throw new UnsupportedOperationException(
                "This method is not supposed to be called when the DTO class is created");
    }

    @Override
    public Copyright createCopyright(@NonNull String projectName, @NonNull String fileName, @NonNull String encoding,
            @NonNull String creator, @NonNull String creationDate) {
        return new DtoCopyright(projectName, fileName, encoding, creator, creationDate);
    }

    @Override
    @Deprecated
    public ClassDescription createClassDescription(@NonNull String creator, @NonNull String version) {
        throw new UnsupportedOperationException(
                "This method is not supposed to be called when the DTO class is created");
    }

    @Override
    public ClassDescription createClassDescription(@NonNull String description, @NonNull String creator,
            @NonNull String version) {
        return new DtoClassDescription(description, creator, version);
    }

    @Override
    public Description createDescription(@NonNull String description) {
        return new DtoDescription(description);
    }

    @Override
    @Deprecated
    public Inheritance createInheritance(@NonNull String literal) {
        throw new UnsupportedOperationException(
                "This method is not supposed to be called when the DTO class is created");
    }

    @Override
    @Deprecated
    public Inheritance createInheritance(@NonNull String literal, @NonNull Generics generics) {
        throw new UnsupportedOperationException(
                "This method is not supposed to be called when the DTO class is created");
    }

    @Override
    @Deprecated
    public Interface createInterface(@NonNull String literal) {
        throw new UnsupportedOperationException(
                "This method is not supposed to be called when the DTO class is created");
    }

    @Override
    @Deprecated
    public Interface createInterface(@NonNull String literal, @NonNull Generics generics) {
        throw new UnsupportedOperationException(
                "This method is not supposed to be called when the DTO class is created");
    }

    @Override
    @Deprecated
    public Generics createGenerics() {
        throw new UnsupportedOperationException(
                "This method is not supposed to be called when the DTO class is created");
    }

    @Override
    @Deprecated
    public EnumDefinition createEnumDefinition(@NonNull String literal) {
        throw new UnsupportedOperationException(
                "This method is not supposed to be called when the DTO class is created");
    }

    @Override
    @Deprecated
    public Enumeration createEnumeration(@NonNull EnumDefinition enumDefinition, @NonNull Description description) {
        throw new UnsupportedOperationException(
                "This method is not supposed to be called when the DTO class is created");
    }

    @Override
    @Deprecated
    public FieldDefinition createFieldDefinition(@NonNull String dataType, @NonNull String variableName) {
        throw new UnsupportedOperationException(
                "This method is not supposed to be called when the DTO class is created");
    }

    @Override
    public FieldDefinition createFieldDefinition(@NonNull String dataType, @NonNull String variableName,
            @NonNull String initialValue) {
        return new DtoFieldDefinition(dataType, variableName, initialValue);
    }

    @Override
    public Field createField(@NonNull FieldDefinition fieldDefinition, @NonNull Description description) {
        return new DtoField(fieldDefinition, description);
    }

    @Override
    public FunctionDescription createFunctionDescription(@NonNull String description) {
        return new DtoMethodDescription(description);
    }

    @Override
    public DescriptionTag createDescriptionTag(@NonNull String variableName, @NonNull String description) {
        return new DtoDescriptionTag(variableName, description);
    }

    @Override
    @Deprecated
    public DescriptionTag createDescriptionTag(@NonNull String variableName, @NonNull String description,
            @NonNull Annotation annotation) {
        throw new UnsupportedOperationException(
                "This method is not supposed to be called when the DTO class is created");
    }

    @Override
    public Constructor createConstructor(@NonNull String functionName,
            @NonNull FunctionDescription functionDescription) {
        return new DtoConstructor(functionName, functionDescription);
    }

    @Override
    public Parameter createParameter(@NonNull String dataType, @NonNull String variableName) {
        return new DtoParameter(dataType, variableName);
    }

    @Override
    public ConstructorProcess createConstructorProcess(@NonNull String variableName) {
        return new DtoConstructorProcess(variableName);
    }

    @Override
    public ConstructorProcess createConstructorProcess(@NonNull String variableName, @NonNull String getterName) {
        return new DtoConstructorProcess(variableName, getterName);
    }

    @Override
    public MethodProcess createMethodProcess(@NonNull String variableName) {
        throw new UnsupportedOperationException(
                "This method is not supposed to be called when the DTO class is created");
    }

    @Override
    public Resource createResource(@NonNull Copyright copyright, @NonNull String packageName,
            @NonNull ClassDescription classDescription, String resourceName) {
        return new DtoResource(copyright, packageName, classDescription, resourceName);
    }
}