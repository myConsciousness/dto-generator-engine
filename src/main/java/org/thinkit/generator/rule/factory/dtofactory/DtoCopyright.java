/**
 * Project Name : Generator<br>
 * File Name : DtoCopyright.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/07<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.factory.dtofactory;

import org.thinkit.common.catalog.Indentation;
import org.thinkit.generator.rule.factory.resource.Copyright;

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
public class DtoCopyright extends Copyright {

    /**
     * HTNLの改行タグ
     */
    private static final String HTML_TAG_RETURN = "<br>";

    /**
     * HTMLのパラグラフタグ
     */
    private static final String HTML_TAG_PARAGRAPH = "<p>";

    /**
     * コンストラクタ
     * 
     * @param projectName  プロジェクト名
     * @param fileName     ファイル名
     * @param encoding     エンコード
     * @param creationDate 作成日付
     * @param creator      作成者
     * 
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public DtoCopyright(String projectName, String fileName, String encoding, String creationDate, String creator) {
        super(projectName, fileName, encoding, creationDate, creator);
    }

    @Override
    public String createResource() {
        final String returnCode = Indentation.returnCode();

        final StringBuilder copyright = new StringBuilder();
        copyright.append("/**").append(returnCode);
        copyright.append(" * ").append("Project Name : ").append(super.getProjectName()).append(HTML_TAG_RETURN)
                .append(returnCode);
        copyright.append(" * ").append("File Name : ").append(super.getFileName()).append(HTML_TAG_RETURN)
                .append(returnCode);
        copyright.append(" * ").append("Encoding : ").append(super.getEncoding()).append(HTML_TAG_RETURN)
                .append(returnCode);
        copyright.append(" * ").append("Creation Date : ").append(super.getCreationDate()).append(HTML_TAG_RETURN)
                .append(returnCode);

        copyright.append(" * ").append(HTML_TAG_PARAGRAPH).append(returnCode);
        copyright.append(" * ").append("Copyright © ").append(super.getCreationYear()).append(Indentation.space())
                .append(super.getCreator()).append(". All rights reserved.").append(returnCode);
        copyright.append(" * ").append(HTML_TAG_PARAGRAPH).append(returnCode);

        copyright.append(" * ").append("This source code or any portion thereof must not be").append(HTML_TAG_RETURN);
        copyright.append(" * ").append("reproduced or used in any manner whatsoever.").append(returnCode);
        copyright.append(" */");

        return copyright.toString();
    }
}