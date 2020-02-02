package org.share.logging.infer.core.listener;

import org.share.logging.infer.core.api.InferOperator;

public interface Refreshable {


    void subscribe(InferOperator inferOperator);
    void unSubscribe(InferOperator inferOperator);
}
