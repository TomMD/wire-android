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
package com.waz.zclient.core.api.scala;

import com.waz.api.IConversation;
import com.waz.api.UpdateListener;
import com.waz.api.User;
import com.waz.api.ZMessagingApi;
import com.waz.zclient.core.stores.connect.ConnectStore;
import com.waz.zclient.core.stores.connect.ConnectStoreObserver;
import java.util.HashMap;
import java.util.Map;

public class ScalaConnectStore extends ConnectStore {

  private ZMessagingApi zMessagingApi;

  Map<UserRequester, User> users;

  public ScalaConnectStore(ZMessagingApi zMessagingApi) {
    this.zMessagingApi = zMessagingApi;
    users = new HashMap<>();
  }

  @Override
  public void tearDown() {
    removeUserListener();
    users = null;
  }

  @Override
  public void addConnectRequestObserver(ConnectStoreObserver connectStoreObserver) {
    super.addConnectRequestObserver(connectStoreObserver);
    for (UserRequester userRequester : users.keySet()) {
      notifyConnectUserUpdated(users.get(userRequester), userRequester);
    }
  }

  @Override
  public void loadUser(String userId, UserRequester userRequester) {
    removeUserListener();
    User user = zMessagingApi.getUser(userId);
    users.put(userRequester, user);

    switch (userRequester) {
      case SEARCH:
        user.addUpdateListener(searchUserListener);
        searchUserListener.updated();
        break;
      case CONVERSATION:
        user.addUpdateListener(conversationUserListener);
        conversationUserListener.updated();
        break;
      case PARTICIPANTS:
        user.addUpdateListener(participantsUserListener);
        participantsUserListener.updated();
        break;
      case POPOVER:
        user.addUpdateListener(popoverUserListener);
        popoverUserListener.updated();
        break;
    }
  }

  @Override
  public IConversation connectToNewUser(User user, String firstMessage) {
    return user.connect(firstMessage);
  }

  private void removeUserListener() {
    User searchUser = users.get(UserRequester.SEARCH);
    if (searchUser != null) {
      searchUser.removeUpdateListener(searchUserListener);
    }

    User conversationUser = users.get(UserRequester.CONVERSATION);
    if (conversationUser != null) {
      conversationUser.removeUpdateListener(conversationUserListener);
    }

    User participantsUser = users.get(UserRequester.PARTICIPANTS);
    if (participantsUser != null) {
      participantsUser.removeUpdateListener(participantsUserListener);
    }

    User popoverUser = users.get(UserRequester.POPOVER);
    if (popoverUser != null) {
      popoverUser.removeUpdateListener(popoverUserListener);
    }
  }

  private UpdateListener searchUserListener =
      new UpdateListener() {
        @Override
        public void updated() {
          if (users.get(UserRequester.SEARCH) != null) {
            User searchUser = users.get(UserRequester.SEARCH);
            notifyConnectUserUpdated(searchUser, UserRequester.SEARCH);
          }
        }
      };

  private UpdateListener participantsUserListener =
      new UpdateListener() {
        @Override
        public void updated() {
          if (users.get(UserRequester.PARTICIPANTS) != null) {
            User participantsUser = users.get(UserRequester.PARTICIPANTS);
            notifyConnectUserUpdated(participantsUser, UserRequester.PARTICIPANTS);
          }
        }
      };
  private UpdateListener popoverUserListener =
      new UpdateListener() {
        @Override
        public void updated() {
          if (users.get(UserRequester.POPOVER) != null) {
            User participantsUser = users.get(UserRequester.POPOVER);
            notifyConnectUserUpdated(participantsUser, UserRequester.POPOVER);
          }
        }
      };

  private UpdateListener conversationUserListener =
      new UpdateListener() {
        @Override
        public void updated() {
          if (users.get(UserRequester.CONVERSATION) != null) {
            User conversationUser = users.get(UserRequester.CONVERSATION);
            notifyConnectUserUpdated(conversationUser, UserRequester.CONVERSATION);
          }
        }
      };
}
