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
package com.waz.zclient.ui.optionsmenu;

import com.waz.zclient.ui.R;

public enum OptionsMenuItem {

  /** ORDER IS IMPORTANT! Collections.sort is used for priority in menus */

  /** OptionsMenuItems for conversation */
  ARCHIVE(R.string.conversation__action__archive, R.string.glyph__archive),
  UNARCHIVE(R.string.conversation__action__unarchive, R.string.glyph__archive),
  BLOCK(R.string.confirmation_menu__confirm_block, R.string.glyph__block),
  UNBLOCK(R.string.connect_request__unblock__button__text, R.string.glyph__block),
  SILENCE(R.string.conversation__action__silence, R.string.glyph__silence),
  UNSILENCE(R.string.conversation__action__unsilence, R.string.glyph__notify),
  PICTURE(R.string.conversation__action__picture, R.string.glyph__camera),
  CALL(R.string.conversation__action__call, R.string.glyph__call),
  DELETE(R.string.conversation__action__delete, R.string.glyph__trash),
  LEAVE(R.string.conversation__action__leave, R.string.glyph__minus);

  public final int resTextId;
  public final int resGlyphId;

  OptionsMenuItem(int resTextId, int resGlyphId) {
    this.resTextId = resTextId;
    this.resGlyphId = resGlyphId;
  }

  /**
   * items in the toggled state (aka not the original state) will return true.
   *
   * @return true if item is a toggled state item
   */
  public boolean isToggled() {
    switch (this) {
      case UNARCHIVE:
        return true;
      case UNSILENCE:
        return true;
      case UNBLOCK:
        return true;
      default:
        return false;
    }
  }
}
