/**
 * Copyright 2015-2016 The OpenZipkin Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package me.j360.trace.storage.core.guava;

import com.google.common.util.concurrent.ListenableFuture;
import me.j360.trace.core.DependencyLink;
import me.j360.trace.core.Span;
import me.j360.trace.core.internal.Nullable;
import me.j360.trace.core.storage.QueryRequest;
import me.j360.trace.core.storage.SpanStore;

import java.util.List;

/**
 * An interface that is equivalent to {@link SpanStore} but exposes methods as
 * {@link ListenableFuture} to allow asynchronous composition.
 *
 * @see SpanStore
 */
public interface GuavaSpanStore {

  /**
   * Version of {@link SpanStore#getTraces} that returns {@link ListenableFuture}.
   */
  ListenableFuture<List<List<Span>>> getTraces(QueryRequest request);

  /**
   * Version of {@link SpanStore#getTrace} that returns {@link ListenableFuture}.
   */
  ListenableFuture<List<Span>> getTrace(long id);

  /**
   * Version of {@link SpanStore#getRawTrace} that returns {@link ListenableFuture}.
   */
  ListenableFuture<List<Span>> getRawTrace(long traceId);

  /**
   * Version of {@link SpanStore#getServiceNames} that returns {@link ListenableFuture}.
   */
  ListenableFuture<List<String>> getServiceNames();

  /**
   * Version of {@link SpanStore#getSpanNames} that returns {@link ListenableFuture}.
   */
  ListenableFuture<List<String>> getSpanNames(String serviceName);

  /**
   * Version of {@link SpanStore#getDependencies} that returns {@link ListenableFuture}.
   */
  ListenableFuture<List<DependencyLink>> getDependencies(long endTs, @Nullable Long lookback);
}
