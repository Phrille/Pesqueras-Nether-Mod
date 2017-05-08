package soupbubbles.pesquerasnethermod.handler;

import java.io.File;

import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import soupbubbles.pesquerasnethermod.lib.Reference;

public class ConfigurationHandler
{

    public static Configuration configuration;

    private static final String CATEGORY_WORLDGEN = "general.worldgen";

    public static void init(File configFile)
    {
        if (configuration == null)
        {
            configuration = new Configuration(configFile, true);
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {
        Settings.generateNucleus = configuration.getBoolean(Settings.GENERATE_NUCLEUS_NAME, CATEGORY_WORLDGEN, Settings.GENERATE_NUCLEUS_DEFAULT, Settings.GENERATE_NUCLEUS_COMMENT, Settings.GENERATE_NUCLEUS_LABEL);
        Settings.generateBeehive = configuration.getBoolean(Settings.GENERATE_BEEHIVE_NAME, CATEGORY_WORLDGEN, Settings.GENERATE_BEEHIVE_DEFAULT, Settings.GENERATE_BEEHIVE_COMMENT, Settings.GENERATE_BEEHIVE_LABEL);
        Settings.generateNetherWells = configuration.getBoolean(Settings.GENERATE_NETHER_WELLS_NAME, CATEGORY_WORLDGEN, Settings.GENERATE_NETHER_WELLS_DEFAULT, Settings.GENERATE_NETHER_WELLS_COMMENT, Settings.GENERATE_NETHER_WELLS_LABEL);

        if (configuration.hasChanged())
        {
            configuration.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equalsIgnoreCase(Reference.MOD_ID))
        {
            loadConfiguration();
        }
    }

    public static class Settings
    {
        public static boolean generateNucleus;
        private static final String GENERATE_NUCLEUS_NAME = "Generate Nucleus";
        private static final String GENERATE_NUCLEUS_LABEL = "generate_nucleus.label";
        private static final String GENERATE_NUCLEUS_COMMENT = "Return true if Nucleus Blocks should be generated in the Nether";
        private static final boolean GENERATE_NUCLEUS_DEFAULT = true;
        
        public static boolean generateBeehive;
        private static final String GENERATE_BEEHIVE_NAME = "Generate Beehives";
        private static final String GENERATE_BEEHIVE_LABEL = "generate_bee_hive.label";
        private static final String GENERATE_BEEHIVE_COMMENT = "Return true if Beehives should be generated in the Nether";
        private static final boolean GENERATE_BEEHIVE_DEFAULT = true;
        
        public static boolean generateNetherWells;
        private static final String GENERATE_NETHER_WELLS_NAME = "Generate Nether Wells";
        private static final String GENERATE_NETHER_WELLS_LABEL = "generate_nether_wells.label";
        private static final String GENERATE_NETHER_WELLS_COMMENT = "Return true if Nether Wells should be generated in the Nether";
        private static final boolean GENERATE_NETHER_WELLS_DEFAULT = true;
    }
}