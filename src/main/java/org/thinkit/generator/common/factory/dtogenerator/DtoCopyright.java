/**
 * Project Name : generator-commons<br>
 * File Name : DtoCopyright.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/07<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.factory.dtogenerator;

import org.thinkit.common.catalog.HtmlTag;
import org.thinkit.common.catalog.Indentation;
import org.thinkit.generator.common.factory.resource.Copyright;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * DTOクラスの著作権を生成する具象クラスです。<br>
 * DTOに必要な著作権を生成する処理を{@link Component#createResource()}に実装します。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * @see Component
 * @see Component#createResource()
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
         * @exception NullPointerException 引数として{@code null}が渡された場合
         */
        public DtoCopyright(String projectName, String fileName, String encoding, String creator, String creationDate) {
                super(projectName, fileName, encoding, creator, creationDate);
        }

        @Override
        public String createResource() {
                final String returnCode = Indentation.returnCode();
                final String htmlTagBreak = HtmlTag.br();
                final String htmlTagParagraph = HtmlTag.p();

                final StringBuilder copyright = new StringBuilder();
                copyright.append("/**").append(returnCode);
                copyright.append(" * ").append("Project Name : ").append(super.getProjectName()).append(htmlTagBreak)
                                .append(returnCode);
                copyright.append(" * ").append("File Name : ").append(super.getFileName()).append(htmlTagBreak)
                                .append(returnCode);
                copyright.append(" * ").append("Encoding : ").append(super.getEncoding()).append(htmlTagBreak)
                                .append(returnCode);
                copyright.append(" * ").append("Creation Date : ").append(super.getCreationDate()).append(htmlTagBreak)
                                .append(returnCode);

                copyright.append(" * ").append(htmlTagParagraph).append(returnCode);
                copyright.append(" * ").append("Copyright © ").append(super.getCreationYear())
                                .append(Indentation.space()).append(super.getCreator()).append(". All rights reserved.")
                                .append(returnCode);
                copyright.append(" * ").append(htmlTagParagraph).append(returnCode);

                copyright.append(" * ").append("This source code or any portion thereof must not be")
                                .append(htmlTagBreak).append(returnCode);
                copyright.append(" * ").append("reproduced or used in any manner whatsoever.").append(returnCode);
                copyright.append(" */");

                return copyright.toString();
        }
}