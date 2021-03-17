//
// This file is part of BetterJails, licensed under the MIT License.
//
// Copyright (c) 2021 Fefo6644 <federico.lopez.1999@outlook.com>
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
//

package com.github.fefo.betterjails.api.impl.model.jail;

import com.github.fefo.betterjails.BetterJailsPlugin;
import com.github.fefo.betterjails.api.model.jail.Jail;
import com.github.fefo.betterjails.api.model.jail.JailManager;
import com.github.fefo.betterjails.util.DataHandler;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class ApiJailManager implements JailManager {

  private final BetterJailsPlugin plugin;

  public ApiJailManager(final BetterJailsPlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  @SuppressWarnings("ConstantConditions")
  public @NotNull Jail createAndSaveJail(final @NotNull String name, final @NotNull Location location) throws IllegalArgumentException {
    Objects.requireNonNull(name, "name");
    Objects.requireNonNull(location, "location");

    final DataHandler dataHandler = this.plugin.dataHandler;
    if (dataHandler.getJail(name) != null) {
      throw new IllegalArgumentException("name");
    }

    try {
      dataHandler.addJail(name, location);
    } catch (final IOException exception) {
      throw new RuntimeException(exception);
    }

    return dataHandler.getJail(name);
  }

  @Override
  public @Nullable Jail getJail(final @NotNull String name) {
    Objects.requireNonNull(name, "name");
    return this.plugin.dataHandler.getJail(name);
  }

  @Override
  public void deleteJail(final @NotNull Jail jail) {
    Objects.requireNonNull(jail, "jail");

    try {
      this.plugin.dataHandler.removeJail(jail.name());
    } catch (final IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  @Override
  public @NotNull @UnmodifiableView Collection<@NotNull Jail> getAllJails() {
    return Collections.unmodifiableCollection(this.plugin.dataHandler.getJails().values());
  }
}
