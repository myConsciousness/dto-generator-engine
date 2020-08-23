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

import org.thinkit.common.catalog.HtmlTag;
import org.thinkit.common.catalog.Indentation;
import org.thinkit.generator.common.factory.resource.Copyright;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * DTOクラスの著作権を生成する具象クラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see Copyright
 */
@ToString
@EqualsAndHashCode(callSuper = false)
final class DtoCopyright extends Copyright {

        /**
         * コンストラクタ
         *
         * @param projectName  プロジェクト名
         * @param fileName     ファイル名
         * @param encoding     エンコード
         * @param creator      作成者
         * @param creationDate 作成日付
         *
         * @exception NullPointerException 引数として {@code null} が渡された場合
         */
        public DtoCopyright(String projectName, String fileName, String encoding, String creator, String creationDate) {
                super(projectName, fileName, encoding, creator, creationDate);
        }

        @Override
        public String createResource() {
                final String htmlTagBreak = HtmlTag.br();
                final String htmlTagParagraph = HtmlTag.p();

                final StringBuilder copyright = new StringBuilder();
                copyright.append("/**");
                copyright.append(" * ").append("Project Name : ").append(super.getProjectName()).append(htmlTagBreak);
                copyright.append(" * ").append("File Name : ").append(super.getFileName()).append(htmlTagBreak);
                copyright.append(" * ").append("Encoding : ").append(super.getEncoding()).append(htmlTagBreak);
                copyright.append(" * ").append("Creation Date : ").append(super.getCreationDate()).append(htmlTagBreak);
                copyright.append(" * ").append(htmlTagParagraph);
                copyright.append(" * ").append("Copyright © ").append(super.getCreationYear())
                                .append(Indentation.space()).append(super.getCreator())
                                .append(". All rights reserved.");
                copyright.append(" * ").append(htmlTagParagraph);

                copyright.append(" * ").append("This source code or any portion thereof must not be")
                                .append(htmlTagBreak);
                copyright.append(" * ").append("reproduced or used in any manner whatsoever.");
                copyright.append(" */");

                return copyright.toString();
        }
}