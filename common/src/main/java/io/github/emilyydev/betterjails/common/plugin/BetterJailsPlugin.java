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

package io.github.emilyydev.betterjails.common.plugin;

import com.google.common.collect.ImmutableList;
import io.github.emilyydev.betterjails.common.command.CommandHandler;
import io.github.emilyydev.betterjails.common.configuration.ConfigurationAdapter;
import io.github.emilyydev.betterjails.common.message.Message;
import io.github.emilyydev.betterjails.common.message.Subject;
import io.github.emilyydev.betterjails.common.message.TranslationManager;
import io.github.emilyydev.betterjails.common.model.cell.CellManager;
import io.github.emilyydev.betterjails.common.model.prisoner.PrisonerManager;
import io.github.emilyydev.betterjails.common.plugin.abstraction.PlatformAdapter;
import io.github.emilyydev.betterjails.common.plugin.abstraction.Player;
import io.github.emilyydev.betterjails.common.plugin.abstraction.TaskScheduler;
import io.github.emilyydev.betterjails.common.storage.StorageProvider;
import net.kyori.adventure.platform.AudienceProvider;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

public final class BetterJailsPlugin {

  public static final Logger LOGGER = LoggerFactory.getLogger(BetterJailsPlugin.class);

  private final CommandHandler commandHandler = new CommandHandler(this);
  private final TranslationManager translationManager = new TranslationManager(this);
  private final BetterJailsBootstrap bootstrapPlugin;

  private ConfigurationAdapter configurationAdapter;
  private TaskScheduler taskScheduler;

  private StorageProvider storageProvider;
  private CellManager cellManager;
  private PrisonerManager prisonerManager;

  public BetterJailsPlugin(final BetterJailsBootstrap bootstrapPlugin) {
    this.bootstrapPlugin = bootstrapPlugin;
  }

  public void load() {
    try {
      this.translationManager.loadTranslations();
    } catch (final IOException exception) {
      // not fatal, the plugin will still work
      exception.printStackTrace();
    }

    try {
      this.configurationAdapter = this.bootstrapPlugin.getConfigurationAdapter();
      this.configurationAdapter.load();
    } catch (final IOException exception) {
      // not fatal, fallback values provided
      exception.printStackTrace();
    }

    this.taskScheduler = this.bootstrapPlugin.getTaskScheduler();
  }

  public void enable() {
    this.configurationAdapter = this.bootstrapPlugin.getConfigurationAdapter();

    getConsoleSubject().sendMessage(Message.STARTUP_BANNER);
    Message.PLUGIN_INFO.send(getConsoleSubject(), this);
  }

  public void disable() {

  }

  public BetterJailsBootstrap getBootstrapPlugin() {
    return this.bootstrapPlugin;
  }

  public AudienceProvider getAudienceProvider() {
    return this.bootstrapPlugin.getAudienceProvider();
  }

  public CommandHandler getCommandHandler() {
    return this.commandHandler;
  }

  public Path getPluginFolder() {
    return this.bootstrapPlugin.getPluginFolder();
  }

  public StorageProvider getStorageProvider() {
    return this.storageProvider;
  }

  public CellManager getCellManager() {
    return this.cellManager;
  }

  public PrisonerManager getPrisonerManager() {
    return this.prisonerManager;
  }

  public ConfigurationAdapter getConfigurationAdapter() {
    return this.configurationAdapter;
  }

  public TaskScheduler getTaskScheduler() {
    return this.taskScheduler;
  }

  public <S, P, L, W> PlatformAdapter<S, P, L, W> getPlatformAdapter() {
    return this.bootstrapPlugin.getPlatformAdapter();
  }

  public Subject getConsoleSubject() {
    return this.bootstrapPlugin.getConsoleSubject();
  }

  public Logger getLogger() {
    return LOGGER;
  }

  public String getVersion() {
    return this.bootstrapPlugin.getVersion();
  }

  public <P> Collection<Player<P>> getOnlinePlayers() {
    return this.bootstrapPlugin.getOnlinePlayers();
  }

  public @Nullable InputStream getResource(final String name) {
    return BetterJailsPlugin.class.getClassLoader().getResourceAsStream(name);
  }

  public @Nullable URL getResourceURL(final String name) {
    return BetterJailsPlugin.class.getClassLoader().getResource(name);
  }

  public List<String> getAuthors() {
    return ImmutableList.of("emilyy-dev");
  }

  public String getWebsite() {
    return "https://github.com/emilyy-dev/BetterJails";
  }
}
