/**
 * Project Name : generator-commons<br>
 * File Name : ContentResourceFactory.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/08/03<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.factory.content;

import org.thinkit.generator.common.factory.json.Item;
import org.thinkit.generator.common.factory.json.ItemGroup;
import org.thinkit.generator.common.factory.json.LeafVertex;
import org.thinkit.generator.common.factory.json.Node;
import org.thinkit.generator.common.factory.json.NodeGroup;
import org.thinkit.generator.common.factory.json.Resource;
import org.thinkit.generator.common.factory.json.ResourceFactory;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * コンテンツリソースの各構成要素を生成するオブジェクトを返却するファクトリクラスです。
 * <p>
 * この {@link ContentResourceFactory} クラスのインスタンスを生成する場合は
 * {@link ContentResourceFactory#getInstance()} メソッドを使用してください。
 * {@link ContentResourceFactory#getInstance()} メソッドは
 * {@link ContentResourceFactory} クラスのシングルトンインスタンスを返却します。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class ContentResourceFactory extends ResourceFactory {

    /**
     * デフォルトコンストラクタ
     */
    private ContentResourceFactory() {
    }

    /**
     * {@link ContentResourceFactory} クラスのシングルトンインスタンスを返却します。
     *
     * @return {@link ContentResourceFactory} クラスのシングルトンインスタンス
     */
    public static ResourceFactory getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * {@link ContentResourceFactory} クラスのインスタンスを保持するためのインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    private static class InstanceHolder {

        /**
         * {@link ContentResourceFactory} クラスのインスタンス
         */
        private static final ResourceFactory INSTANCE = new ContentResourceFactory();
    }

    @Override
    public Item createItem(@NonNull String key, @NonNull String value) {
        return new ContentItem(key, value);
    }

    @Override
    public ItemGroup createItemGroup() {
        return new ContentItemGroup();
    }

    @Override
    public Node createNode(@NonNull ItemGroup itemGroup, @NonNull NodeGroup nodeGroup) {
        return new ContentNode(itemGroup, nodeGroup);
    }

    @Override
    public NodeGroup createNodeGroup(@NonNull String key) {
        return new ContentNodeGroup(key);
    }

    @Override
    public LeafVertex createLeafVertex() {
        return new ContentLeafVertex();
    }

    @Override
    public Resource createResource(@NonNull LeafVertex leafVertex) {
        return new ContentResource(leafVertex);
    }
}