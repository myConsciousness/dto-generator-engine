package org.thinkit.generator.common.command.contentgenerator;

import org.thinkit.common.command.Command;
import org.thinkit.generator.common.vo.content.ContentMatrix;
import org.thinkit.generator.common.vo.content.ContentResource;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = false)
public final class ContentResourceFormatter implements Command<ContentResource> {

    /**
     * コンテンツマトリクス
     */
    private ContentMatrix contentMatrix;

    /**
     * コンテンツリソース
     */
    @Getter
    private ContentResource contentResource;

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
    public static ContentResourceFormatter of(@NonNull ContentMatrix contentMatrix) {
        return new ContentResourceFormatter(contentMatrix);
    }

    @Override
    public ContentResource run() {
        return null;
    }
}