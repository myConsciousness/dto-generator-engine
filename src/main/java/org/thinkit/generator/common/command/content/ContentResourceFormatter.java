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

package org.thinkit.generator.common.command.content;

import org.thinkit.common.command.Command;
import org.thinkit.formatter.JsonFormatter;
import org.thinkit.generator.common.catalog.content.CreatorKey;
import org.thinkit.generator.common.catalog.content.GroupKey;
import org.thinkit.generator.common.catalog.content.MetaKey;
import org.thinkit.generator.common.catalog.content.SelectionNodeKey;
import org.thinkit.generator.common.catalog.content.VersionKey;
import org.thinkit.generator.common.factory.content.ContentResourceFactory;
import org.thinkit.generator.common.factory.json.ItemGroup;
import org.thinkit.generator.common.factory.json.LeafVertex;
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
    public ContentResource execute() {

        final ResourceFactory factory = ContentResourceFactory.getInstance();

        final LeafVertex leafVertex = factory.createLeafVertex();
        leafVertex.add(this.createCreatorNodeGroup());
        leafVertex.add(this.createVersionNodeGroup());
        leafVertex.add(this.createMetaNodeGroup());
        leafVertex.add(this.createSelectionNodeGroup());

        final ContentMeta contentMeta = this.contentMatrix.getContentMeta();

        return ContentResource.of(contentMeta.getPackageName(), contentMeta.getContentName(),
                JsonFormatter.of().format(factory.createResource(leafVertex).createResource()));
    }

    /**
     * コンテンツの {@code "creator"} キーに紐づく作成者ノードグループを生成し返却します。
     *
     * @return 作成者ノードグループ
     */
    private NodeGroup createCreatorNodeGroup() {

        final ContentCreator ContentCreator = this.contentMatrix.getContentCreator();
        final ResourceFactory factory = ContentResourceFactory.getInstance();

        final ItemGroup itemGroup = factory.createItemGroup();
        itemGroup.add(factory.createItem(CreatorKey.author(), ContentCreator.getAuthor()));
        itemGroup.add(factory.createItem(CreatorKey.creationDate(), ContentCreator.getCreationDate()));
        itemGroup.add(factory.createItem(CreatorKey.updateDate(), ContentCreator.getUpdateDate()));

        return factory.createNodeGroup(GroupKey.creator()).add(factory.createNode(itemGroup));
    }

    /**
     * コンテンツの {@code "version"} キーに紐づくバージョンノードグループを生成し返却します。
     *
     * @return バージョンノードグループ
     */
    private NodeGroup createVersionNodeGroup() {

        final ContentVersion contentVersion = this.contentMatrix.getContentVersion();
        final ResourceFactory factory = ContentResourceFactory.getInstance();

        final ItemGroup itemGroup = factory.createItemGroup();
        itemGroup.add(factory.createItem(VersionKey.since(), contentVersion.getSince()));
        itemGroup.add(factory.createItem(VersionKey.version(), contentVersion.getVersion()));

        return factory.createNodeGroup(GroupKey.version()).add(factory.createNode(itemGroup));
    }

    /**
     * コンテンツの {@code "meta"} キーに紐づくメタノードグループを生成し返却します。
     *
     * @return メタノードグループ
     */
    private NodeGroup createMetaNodeGroup() {

        final ContentMeta contentMeta = this.contentMatrix.getContentMeta();
        final ResourceFactory factory = ContentResourceFactory.getInstance();

        final ItemGroup itemGroup = factory.createItemGroup();
        itemGroup.add(factory.createItem(MetaKey.packageName(), contentMeta.getPackageName()));
        itemGroup.add(factory.createItem(MetaKey.contentName(), contentMeta.getContentName()));
        itemGroup.add(factory.createItem(MetaKey.encoding(), contentMeta.getEncoding()));
        itemGroup.add(factory.createItem(MetaKey.description(), contentMeta.getDescription()));

        return factory.createNodeGroup(GroupKey.meta()).add(factory.createNode(itemGroup));
    }

    /**
     * コンテンツの {@code "selectionNodes"} キーに紐づく選択ノードグループを生成し返却します。
     *
     * @return 選択ノードグループ
     */
    private NodeGroup createSelectionNodeGroup() {

        final ResourceFactory factory = ContentResourceFactory.getInstance();
        final NodeGroup selectionNodeGroup = factory.createNodeGroup(GroupKey.selectionNodes()).toArray();

        this.contentMatrix.getContentSelectionNodeGroup().forEach(contentSelectionNode -> {

            final ItemGroup itemGroup = factory.createItemGroup();
            itemGroup.add(factory.createItem(SelectionNodeKey.conditionId(), contentSelectionNode.getConditionId()));

            contentSelectionNode.getContentSelectionGroup().forEach(contentSelection -> {
                itemGroup.add(factory.createItem(contentSelection.getKey(), contentSelection.getValue()));
            });

            selectionNodeGroup.add(
                    factory.createNode(factory.createNodeGroup(GroupKey.node()).add(factory.createNode(itemGroup))));
        });

        return selectionNodeGroup;
    }
}
