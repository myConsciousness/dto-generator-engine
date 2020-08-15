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

package org.thinkit.generator.common.factory.content;

import org.apache.commons.lang3.StringUtils;
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
    public Node createNode(@NonNull ItemGroup itemGroup) {
        return new ContentNode(itemGroup, this.createNodeGroup(StringUtils.EMPTY));
    }

    @Override
    public Node createNode(@NonNull NodeGroup nodeGroup) {
        return new ContentNode(this.createItemGroup(), nodeGroup);
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