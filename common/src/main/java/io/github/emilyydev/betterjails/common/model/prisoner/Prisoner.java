//
// This file is part of BetterJails, licensed under the MIT License.
//
// Copyright (c) emilyy-dev
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

package io.github.emilyydev.betterjails.common.model.prisoner;

import io.github.emilyydev.betterjails.common.plugin.abstraction.Location;
import io.github.emilyydev.betterjails.common.plugin.abstraction.Player;
import net.kyori.adventure.audience.Audience;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public abstract class Prisoner<P> extends Player<P> {

  private final Set<String> groups;
  private final String jailedBy;
  private final String jail;
  private final Duration jailedFor;
  private final Instant jailedUntil;
  private final Location lastLocation;

  public Prisoner(final UUID uuid, final @Nullable String name,
                  final String jailedBy, final Location lastLocation,
                  final Duration duration, final Set<String> groups,
                  final String jail, final Audience audience, final P player) {
    super(uuid, name, audience, player);

    this.groups = groups;
    this.jail = jail;
    this.jailedBy = Objects.requireNonNull(jailedBy, "jailedBy");
    this.jailedFor = duration;
    this.jailedUntil = Instant.now().plus(duration);
    this.lastLocation = Objects.requireNonNull(lastLocation, "lastLocation");
  }

  @Override
  public boolean isJailed() {
    return true;
  }

  @Override
  public Prisoner<P> asPrisoner() {
    return this;
  }

  public Set<String> groups() {
    return this.groups;
  }

  public String jailedBy() {
    return this.jailedBy;
  }

  public String jail() {
    return this.jail;
  }

  public Duration jailedFor() {
    return this.jailedFor;
  }

  public Instant jailedUntil() {
    return this.jailedUntil;
  }

  public Location lastLocation() {
    return this.lastLocation;
  }
}
