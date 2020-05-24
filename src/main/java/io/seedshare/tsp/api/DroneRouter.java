/*
 * Copyright 2020 kevvurs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.seedshare.tsp.api;

import io.seedshare.tsp.model.ServiceDestination;
import java.util.List;

/** Approximates the optimal path for servicing a set of customers by drone. */
public interface DroneRouter {

  /**
   * Find an optimal order to visit each service destination.
   *
   * @param distributionCenter
   * @param serviceDestinations locations to visit
   * @return ordered list of service destinations, first to last visited
   */
  List<ServiceDestination> route(
      ServiceDestination distributionCenter, List<ServiceDestination> serviceDestinations);
}
