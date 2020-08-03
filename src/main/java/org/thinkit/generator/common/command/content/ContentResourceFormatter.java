/**
 * Project Name : generator-commons<br>
 * File Name : ContentResourceFormatter.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/08/03<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.command.content;

import org.thinkit.common.command.Command;
import org.thinkit.generator.common.factory.content.ContentResourceFactory;
import org.thinkit.generator.common.factory.json.NodeGroup;
import org.thinkit.generator.common.factory.json.ResourceFactory;
import org.thinkit.generator.common.vo.content.ContentCreator;
import org.thinkit.generator.common.vo.content.ContentMatrix;
import org.thinkit.generator.common.vo.content.ContentMeta;
import org.thinkit.generator.common.vo.content.ContentResource;
import org.thinkit.generator.common.vo.content.ContentVersion;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * {@link ContentMatrix}
 * オブジェクトに格納されたコンテンツ定義情報を分析し、jsonファイルへ出力する形式へ整形する処理を定義したコマンドクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class ContentResourceFormatter implements Command<ContentResource> {

    /**
     * コンテンツマトリクス
     */
    private ContentMatrix contentMatrix;

    /**
     * デフォルトコンストラクタ
     */
    private ContentResourceFormatter() {
    }

    /**
     * コンストラクタ
     *
     * @param contentMatrix コンテンツリソースへ整形する情報を格納したコンテンツマトリクス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentResourceFormatter(@NonNull ContentMatrix contentMatrix) {
        this.contentMatrix = contentMatrix;
    }

    /**
     * 引数として渡された {@code contentMatrix} オブジェクトをもとに {@link ContentResourceFormatter}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param contentMatrix コンテンツリソースへ整形する情報を格納したコンテンツマトリクス
     * @return {@link ContentResourceFormatter} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static Command<ContentResource> of(@NonNull ContentMatrix contentMatrix) {
        return new ContentResourceFormatter(contentMatrix);
    }

    @Override
    public ContentResource run() {

        final ContentVersion contentVersion = this.contentMatrix.getContentVersion();
        final ContentMeta contentMeta = this.contentMatrix.getContentMeta();

        return ContentResource.of("", "", "");
    }

    private NodeGroup createCreatorNodeGroup() {

        final ContentCreator ContentCreator = this.contentMatrix.getContentCreator();
        final ResourceFactory factory = ContentResourceFactory.getInstance();

        final NodeGroup nodeGroup = factory.createNodeGroup("creator");

        return nodeGroup;
    }
}