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
package com.waz.zclient.core.stores.inappnotification;

import com.waz.api.ErrorsList;
import java.util.HashSet;
import java.util.Set;

public abstract class InAppNotificationStore implements IInAppNotificationStore {
  protected Set<SyncErrorObserver> inAppNotificationObservers = new HashSet<>();

  @Override
  public void addInAppNotificationObserver(SyncErrorObserver messageListener) {
    inAppNotificationObservers.add(messageListener);
  }

  @Override
  public void removeInAppNotificationObserver(SyncErrorObserver messageListener) {
    inAppNotificationObservers.remove(messageListener);
  }

  protected void notifySyncErrorObservers(ErrorsList.ErrorDescription error) {
    for (SyncErrorObserver observer : inAppNotificationObservers) {
      observer.onSyncError(error);
    }
  }
}
