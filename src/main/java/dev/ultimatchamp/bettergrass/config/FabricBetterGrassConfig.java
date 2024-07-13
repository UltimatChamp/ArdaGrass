package dev.ultimatchamp.bettergrass.config;

import dev.isxander.yacl3.api.NameableEnum;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.autogen.*;
import dev.isxander.yacl3.config.v2.api.autogen.Boolean;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.platform.YACLPlatform;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FabricBetterGrassConfig {
    private static final ConfigClassHandler<FabricBetterGrassConfig> HANDLER = ConfigClassHandler.createBuilder(FabricBetterGrassConfig.class)
            .id(YACLPlatform.rl("bettergrass", "bettergrass_config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("bettergrass.json5"))
                    .setJson5(true)
                    .build())
            .build();

    @SerialEntry(comment = "General\nOFF/FAST/FANCY (default: FANCY)")
    @AutoGen(category = "config")
    @EnumCycler()
    public BetterGrassMode betterGrassMode = BetterGrassMode.FANCY;

    public static enum BetterGrassMode implements NameableEnum {
        OFF("options.off"),
        FAST("options.graphics.fast"),
        FANCY("options.graphics.fancy");

        private final String displayName;
        BetterGrassMode(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public Text getDisplayName() {
            return Text.translatable(displayName);
        }
    }

    @SerialEntry(comment = "Advanced")
    public static List<String> grassBlocks =
            List.of("minecraft:grass_block",
                    "minecraft:podzol",
                    "minecraft:mycelium",
                    "minecraft:crimson_nylium",
                    "minecraft:warped_nylium");

    @SerialEntry(comment = "Additional\n(default: false)")
    @AutoGen(category = "additional", group = "connectedblocks")
    @CustomName("block.minecraft.dirt_path")
    @Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
    public boolean dirtPaths = false;

    @SerialEntry(comment = "(default: false)")
    @AutoGen(category = "additional", group = "connectedblocks")
    @CustomName("block.minecraft.farmland")
    @Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
    public boolean farmLands = false;

    @SerialEntry(comment = "(default: true)")
    @AutoGen(category = "additional", group = "connectedblocks")
    @CustomName("block.minecraft.snow")
    @Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
    public boolean snowy = true;

    public static void load() {
        HANDLER.load();
    }

    public static void save() {
        HANDLER.save();
    }

    public static FabricBetterGrassConfig instance() {
        return HANDLER.instance();
    }

    public static Screen createScreen(@Nullable Screen parent) {
        return HANDLER.generateGui().generateScreen(parent);
    }
    public static Screen createConfigScreen(Screen parent) {
        return createScreen(parent);
    }
}
