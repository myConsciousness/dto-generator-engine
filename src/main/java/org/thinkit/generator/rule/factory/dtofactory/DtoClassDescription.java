/**
 * Project Name : Generator<br>
 * File Name : DtoClassDescription.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/04<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.factory.dtofactory;

import org.thinkit.generator.catalog.dtogenerator.DtoAnnotation;
import org.thinkit.generator.rule.factory.resource.ClassDescription;
import org.thinkit.common.catalog.Indentation;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * DTOクラスのクラスに関する説明を生成する具象クラスです。<br>
 * DTOに必要なクラスに関する説明を生成する処理を{@link Component#createResource()}に実装します。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * @see Component
 * @see Component#createResource()
 * @see ClassDescription
 */
@ToString
@EqualsAndHashCode(callSuper = false)
final class DtoClassDescription extends ClassDescription {

    /**
     * 初期バージョン
     */
    private static final String INITIAL_VERSION = "1.0";

    /**
     * コンストラクタ
     * 
     * @param description クラスの説明
     * @param creator     クラスの作成者
     * @param version     クラスのバージョン
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public DtoClassDescription(String description, String creator, String version) {
        super(description, creator, version);
    }

    @Override
    public String createResource() {
        final String space = Indentation.space();
        final String returnCode = Indentation.returnCode();

        final StringBuilder classDescription = new StringBuilder();
        classDescription.append("/**").append(returnCode);
        classDescription.append(" * ").append(super.getDescription()).append(returnCode);
        classDescription.append(" *").append(returnCode);
        classDescription.append(" * ").append(DtoAnnotation.author()).append(space).append(super.getCreator())
                .append(returnCode);
        classDescription.append(" * ").append(DtoAnnotation.since()).append(space).append(INITIAL_VERSION)
                .append(returnCode);
        classDescription.append(" * ").append(DtoAnnotation.version()).append(space).append(super.getVersion())
                .append(returnCode);
        classDescription.append(" */");

        return classDescription.toString();
    }
}