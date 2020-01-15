/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.streaming.connectors.gcp.pubsub.common;

import com.google.pubsub.v1.PubsubMessage;
import java.io.Serializable;
import org.apache.flink.api.java.typeutils.ResultTypeQueryable;

/**
 * The deserialization schema describes how to turn the PubsubMessages into data types (Java/Scala
 * objects) that are processed by Flink.
 *
 * @param <T> The type created by the deserialization schema.
 */
public interface PubSubDeserializationSchema<T> extends Serializable, ResultTypeQueryable<T> {

  /**
   * Method to decide whether the element signals the end of the stream. If true is returned the
   * element won't be emitted.
   *
   * @param nextElement The element to test for the end-of-stream signal.
   * @return True, if the element signals end of stream, false otherwise.
   */
  boolean isEndOfStream(T nextElement);

  /**
   * Deserializes a PubsubMessage.
   *
   * @param message PubsubMessage to be deserialized.
   * @return The deserialized message as an object (null if the message cannot be deserialized).
   */
  T deserialize(PubsubMessage message) throws Exception;
}