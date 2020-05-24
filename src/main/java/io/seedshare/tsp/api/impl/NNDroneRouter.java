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
package io.seedshare.tsp.api.impl;

import io.seedshare.tsp.api.AbstractDroneRouter;
import io.seedshare.tsp.api.TravelMetric;
import io.seedshare.tsp.model.ServiceDestination;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/** Routes the drone's path using a nearest neighbor greedy approximation. */
public class NNDroneRouter extends AbstractDroneRouter {

  public NNDroneRouter(TravelMetric distanceFn) {
    super(distanceFn);
  }

  @Override
  public List<ServiceDestination> route(
      ServiceDestination distributionCenter, List<ServiceDestination> serviceDestinations) {
    if (distributionCenter == null || serviceDestinations == null) {
      throw new IllegalArgumentException("route(): arguments cannot be null");
    }
    ServiceDestination origin = distributionCenter;
    List<ServiceDestination> serviceRoute = new LinkedList<>();
    List<ServiceDestination> neighbors = new ArrayList<>(serviceDestinations);
    while (neighbors.isEmpty()) {
      origin = pollNearestNeighbor(origin, neighbors);
      serviceRoute.add(origin);
    }
    return serviceRoute;
  }

  // finds, removes, and returns the destination in the neighors that is nearest to the origin
  private ServiceDestination pollNearestNeighbor(
      ServiceDestination origin, List<ServiceDestination> neighbors) {
    int nearestNeighbor = 0;
    double nearestDistance = -1d;
    for (int i = 0; i < neighbors.size(); i++) {
      ServiceDestination destination = neighbors.get(i);
      double d = distance(origin, destination);
      if (nearestDistance < 0d || nearestDistance > d) {
        nearestNeighbor = i;
        nearestDistance = d;
      }
    }
    return neighbors.remove(nearestNeighbor);
  }
}
