package es.thorifishing;

import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

@Mod(Thorifishing.MOD_ID)
public final class Thorifishing {
    public static final String MOD_ID = "thorifishing";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Thorifishing() {
        LOGGER.info("Thorifishing 0.1.0 loaded: standalone technical foundation");
    }
}
