package net.moying.randomthings.core.asm;

import cpw.mods.modlauncher.LaunchPluginHandler;
import cpw.mods.modlauncher.Launcher;
import cpw.mods.modlauncher.api.IEnvironment;
import cpw.mods.modlauncher.api.ITransformationService;
import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.IncompatibleEnvironmentException;
import cpw.mods.modlauncher.serviceapi.ILaunchPluginService;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LoadingPluginService implements ITransformationService {

    static {
        System.out.println("MagneticCoreMod loading in ModLauncher mode.");
        System.out.println("Replacing launchPlugins map.");
        try {
            Field launchPluginsField = Launcher.class.getDeclaredField("launchPlugins");
            launchPluginsField.setAccessible(true);
            Object launchPlugins = launchPluginsField.get(Launcher.INSTANCE);
            launchPluginsField = LaunchPluginHandler.class.getDeclaredField("plugins");
            launchPluginsField.setAccessible(true);
            MagneticPluginMap<String, ILaunchPluginService> fucked = new MagneticPluginMap<>();
            fucked.putAll(((Map<String, ILaunchPluginService>) launchPluginsField.get(launchPlugins)));
            launchPluginsField.set(launchPlugins, fucked);
            System.out.println("Success. MagneticCoreMod is loaded.");
        } catch (Exception e) {
            System.out.println("Failed to load launch plugins map.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull String name() {
        return "MagneticCoreModLoadingPluginService";
    }

    @Override
    public void initialize(IEnvironment environment) {
    }

    @Override
    public void onLoad(IEnvironment env, Set<String> otherServices) throws IncompatibleEnvironmentException {
    }

    @Override
    public @NotNull List<? extends ITransformer<?>> transformers() {
        return Collections.emptyList();
    }
}
