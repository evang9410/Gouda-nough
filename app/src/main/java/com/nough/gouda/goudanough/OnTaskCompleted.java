package com.nough.gouda.goudanough;

import com.nough.gouda.goudanough.beans.Restaurant;

/**
 * Created by 1432581 on 11/30/2016.
 */

public interface OnTaskCompleted {
    void setNearbyRestaurants(Restaurant[] nearby_restaurants);
}
