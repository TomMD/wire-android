/**
 * Wire Copyright (C) 2018 Wire Swiss GmbH
 *
 * <p>This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * <p>This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * <p>You should have received a copy of the GNU General Public License along with this program. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package com.waz.zclient.pages.main.conversationpager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.waz.zclient.R;

public class ViewPagerScroller extends Scroller {
  private int scrollDuration;
  private double scrollFactor = 1;

  public ViewPagerScroller(Context context) {
    super(context);
    init(context);
  }

  public ViewPagerScroller(Context context, Interpolator interpolator) {
    super(context, interpolator);
    init(context);
  }

  @SuppressLint("NewApi")
  public ViewPagerScroller(Context context, Interpolator interpolator, boolean flywheel) {
    super(context, interpolator, flywheel);
    init(context);
  }

  /** Set the factor by which the duration will change */
  public void setScrollDurationFactor(double scrollFactor) {
    this.scrollFactor = scrollFactor;
  }

  @Override
  public void startScroll(int startX, int startY, int dx, int dy, int duration) {
    super.startScroll(startX, startY, dx, dy, (int) (scrollDuration * scrollFactor));
  }

  private void init(Context context) {
    scrollDuration = context.getResources().getInteger(R.integer.calling_animation_duration_long);
  }
}
