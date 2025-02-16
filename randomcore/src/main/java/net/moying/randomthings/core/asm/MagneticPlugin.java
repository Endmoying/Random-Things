package net.moying.randomthings.core.asm;

import cpw.mods.modlauncher.serviceapi.ILaunchPluginService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.EnumSet;

import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.IRETURN;

public class MagneticPlugin implements ILaunchPluginService {
    public static final MagneticPlugin INSTANCE = new MagneticPlugin();
    public static Logger logger = LogManager.getLogger(MagneticPlugin.class.getName());
    final String asmHandler = "net/moying/magnetic/handler/AsmHandler";

    @Override
    public String name() {
        return "MagneticPlugin";
    }

    @Override
    public EnumSet<Phase> handlesClass(Type classType, boolean isEmpty) {
        return EnumSet.of(Phase.AFTER);
    }

    @Override
    public boolean processClass(Phase phase, ClassNode classNode, Type classType, String reason) {
        if (phase.equals(Phase.BEFORE) || !reason.equals("classloading")) {
            return false;
        }
        boolean ret = false;
        if (classNode.name.equals("net/minecraft/server/level/ServerPlayerGameMode")) {
            logger.log(Level.DEBUG, "Found ServerPlayerGameMode Class: " + classNode.name);

            MethodNode destroyBlock = null;

            for (MethodNode mn : classNode.methods) {
                if (mn.name.equals("destroyBlock"))
                    destroyBlock = mn;

            }
            if (destroyBlock != null) {
                logger.log(Level.DEBUG, " - Found destroyBlock");

                for (int i = 0; i < destroyBlock.instructions.size(); i++) {
                    AbstractInsnNode ain = destroyBlock.instructions.get(i);

                    if (ain.getOpcode() == IRETURN) {
                        InsnList endInsert = new InsnList();
                        endInsert.add(new MethodInsnNode(INVOKESTATIC, asmHandler, "postHarvest", "()V", false));

                        destroyBlock.instructions.insertBefore(ain, endInsert);
                        i += 1;
                    }
                }
            }
        }
        return ret;
    }
}
