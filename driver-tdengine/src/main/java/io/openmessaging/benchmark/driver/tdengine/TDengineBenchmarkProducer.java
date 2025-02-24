/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.openmessaging.benchmark.driver.tdengine;

import io.openmessaging.benchmark.driver.BenchmarkProducer;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class TDengineBenchmarkProducer implements BenchmarkProducer {
    private TDengineProducer tdProducer;

    public TDengineBenchmarkProducer(TDengineProducer producer) {
        this.tdProducer = producer;
    }

    @Override
    public CompletableFuture<Void> sendAsync(Optional<String> key, byte[] payload) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        try {
            tdProducer.send(payload);
            future.complete(null);
        } catch (InterruptedException e) {
            future.completeExceptionally(e);
        }
        return future;
    }

    @Override
    public void close() throws Exception {
        tdProducer.close();
    }
}
