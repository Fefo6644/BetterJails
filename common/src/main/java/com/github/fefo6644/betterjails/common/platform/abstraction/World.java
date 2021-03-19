//
// This file is part of BetterJails, licensed under the MIT License.
//
// Copyright (c) Fefo6644 <federico.lopez.1999@outlook.com>
// Copyright (c) contributors
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

package com.github.fefo6644.betterjails.common.platform.abstraction;

import org.apache.commons.lang.Validate;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class World {

  public static World world(final @NotNull String name, final @NotNull UUID uuid) {
    Validate.notNull(name, "name");
    Validate.notNull(uuid, "uuid");
    return new World(name, uuid);
  }

  private final String name;
  private final UUID uuid;

  private World(final String name, final UUID uuid) {
    this.name = name;
    this.uuid = uuid;
  }

  public @NotNull String name() {
    return this.name;
  }

  public @NotNull UUID uuid() {
    return this.uuid;
  }
}