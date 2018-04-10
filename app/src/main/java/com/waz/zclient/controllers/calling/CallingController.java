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
package com.waz.zclient.controllers.calling;

import java.util.HashSet;
import java.util.Set;

public class CallingController implements ICallingController {
  private Set<CallingObserver> observers = new HashSet<>();

  @Override
  public void addCallingObserver(CallingObserver callingObserver) {
    observers.add(callingObserver);
  }

  @Override
  public void removeCallingObserver(CallingObserver callingObserver) {
    observers.remove(callingObserver);
  }

  @Override
  public void tearDown() {
    observers.clear();
  }

  @Override
  public void startCall(boolean withVideo) {
    for (CallingObserver observer : observers) {
      observer.onStartCall(withVideo);
    }
  }
}
