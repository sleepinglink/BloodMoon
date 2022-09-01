package lotr.client.model;

import java.util.HashMap;
import java.util.Map;
import org.lwjgl.opengl.GL11;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import lotr.common.LOTRMod;
import lotr.common.entity.npc.LOTREntityNPC;
import lotr.common.item.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;

public class LOTRArmorModels {
    public static LOTRArmorModels INSTANCE;
    private Map<ModelBiped, Map<Item, ModelBiped>> specialArmorModels = new HashMap<ModelBiped, Map<Item, ModelBiped>>();

    public static void setupArmorModels() {
        INSTANCE = new LOTRArmorModels();
    }

    private LOTRArmorModels() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    private Map<Item, ModelBiped> getSpecialModels(ModelBiped key) {
        Map<Item, ModelBiped> map = this.specialArmorModels.get(key);
        if(map == null) {
            map = new HashMap<Item, ModelBiped>();
            map.put(LOTRMod.helmetkhand, new LOTRModelKhandHelmet(1.0f, false));
            map.put(LOTRMod.helmet_wagon, new LOTRModelWagonHelmet(1.0f));
            map.put(LOTRMod.helmetrudaur, new LOTRModelRudaurHelmet(1.0f));
            map.put(LOTRMod.helmetUrukdg, new LOTRModelUrukDGHelmet(1.0f));
            map.put(LOTRMod.helmetNumenor, new LOTRModelNumenorHelmet(1.0f));
            map.put(LOTRMod.leatherHat, new LOTRModelLeatherHat());
            map.put(LOTRMod.helmetGondor, new LOTRModelGondorHelmet(1.0f));
            map.put(LOTRMod.helmetElven, new LOTRModelGaladhrimHelmet(1.0f));
            map.put(LOTRMod.helmetGondorWinged, new LOTRModelWingedHelmet(1.0f));
            map.put(LOTRMod.helmetMorgul, new LOTRModelMorgulHelmet(1.0f));
            map.put(LOTRMod.helmetGemsbok, new LOTRModelGemsbokHelmet(1.0f));
            map.put(LOTRMod.helmetHighElven, new LOTRModelHighElvenHelmet(1.0f));
            map.put(LOTRMod.helmetBlackUruk, new LOTRModelBlackUrukHelmet(1.0f));
            map.put(LOTRMod.helmetUruk, new LOTRModelUrukHelmet(1.0f));
            map.put(LOTRMod.helmetNearHaradWarlord, new LOTRModelNearHaradWarlordHelmet(1.0f));
            map.put(LOTRMod.helmetDolAmroth, new LOTRModelSwanHelmet(1.0f));
            map.put(LOTRMod.bodyDolAmroth, new LOTRModelSwanChestplate(1.0f));
            map.put(LOTRMod.helmetMoredainLion, new LOTRModelMoredainLionHelmet(1.0f));
            map.put(LOTRMod.helmetHaradRobes, new LOTRModelHaradTurban());
            map.put(LOTRMod.bodyHaradRobes, new LOTRModelHaradRobes(1.0f));
            map.put(LOTRMod.legsHaradRobes, new LOTRModelHaradRobes(0.5f));
            map.put(LOTRMod.bootsHaradRobes, new LOTRModelHaradRobes(1.0f));
            map.put(LOTRMod.helmetGondolin, new LOTRModelGondolinHelmet(1.0f));
            map.put(LOTRMod.helmetRohanMarshal, new LOTRModelRohanMarshalHelmet(1.0f));
            map.put(LOTRMod.helmetTauredainChieftain, new LOTRModelTauredainChieftainHelmet(1.0f));
            map.put(LOTRMod.helmetTauredainGold, new LOTRModelTauredainGoldHelmet(1.0f));
            map.put(LOTRMod.helmetGundabadUruk, new LOTRModelGundabadUrukHelmet(1.0f));
            map.put(LOTRMod.helmetUrukBerserker, new LOTRModelUrukHelmet(1.0f));
            map.put(LOTRMod.helmetDorwinionElf, new LOTRModelDorwinionElfHelmet(1.0f));
            map.put(LOTRMod.partyHat, new LOTRModelPartyHat(0.6f));
            map.put(LOTRMod.helmetArnor, new LOTRModelArnorHelmet(1.0f));
            map.put(LOTRMod.helmetRhunGold, new LOTRModelEasterlingHelmet(1.0f, false));
            map.put(LOTRMod.helmetRhunWarlord, new LOTRModelEasterlingHelmet(1.0f, true));
            map.put(LOTRMod.helmetRivendell, new LOTRModelHighElvenHelmet(1.0f));
            map.put(LOTRMod.bodyGulfHarad, new LOTRModelGulfChestplate(1.0f));
            map.put(LOTRMod.helmetUmbar, new LOTRModelUmbarHelmet(1.0f));
            map.put(LOTRMod.helmetHarnedor, new LOTRModelHarnedorHelmet(1.0f));
            map.put(LOTRMod.bodyHarnedor, new LOTRModelHarnedorChestplate(1.0f));
            map.put(LOTRMod.helmetBlackNumenorean, new LOTRModelBlackNumenoreanHelmet(1.0f));
            map.put(LOTRMod.plate, new LOTRModelHeadPlate());
            map.put(LOTRMod.woodPlate, new LOTRModelHeadPlate());
            map.put(LOTRMod.ceramicPlate, new LOTRModelHeadPlate());
            for(ModelBiped armorModel : map.values()) {
                this.copyModelRotations(armorModel, key);
            }
            this.specialArmorModels.put(key, map);
        }
        return map;
    }

    public ModelBiped getSpecialArmorModel(ItemStack itemstack, int slot, EntityLivingBase entity, ModelBiped mainModel) {
        if(itemstack == null) {
            return null;
        }
        Item item = itemstack.getItem();
        ModelBiped model = this.getSpecialModels(mainModel).get(item);
        if(model == null) {
            return null;
        }
        if(model instanceof LOTRModelLeatherHat) {
            ((LOTRModelLeatherHat) model).setHatItem(itemstack);
        }
        if(model instanceof LOTRModelHaradRobes) {
            ((LOTRModelHaradRobes) model).setRobeItem(itemstack);
        }
        if(model instanceof LOTRModelPartyHat) {
            ((LOTRModelPartyHat) model).setHatItem(itemstack);
        }
        if(model instanceof LOTRModelHeadPlate) {
            ((LOTRModelHeadPlate) model).setPlateItem(itemstack);
        }
        this.copyModelRotations(model, mainModel);
        this.setupArmorForSlot(model, slot);
        return model;
    }

    @SubscribeEvent
    public void getPlayerArmorModel(RenderPlayerEvent.SetArmorModel event) {
        RenderPlayer renderer = event.renderer;
        ModelBiped mainModel = renderer.modelBipedMain;
        EntityPlayer entityplayer = event.entityPlayer;
        ItemStack armor = event.stack;
        int slot = event.slot;
        int result = this.getEntityArmorModel(renderer, mainModel, entityplayer, armor, 3 - slot);
        if(result > 0) {
            event.result = result;
        }
    }

    public int getEntityArmorModel(RendererLivingEntity renderer, ModelBiped mainModel, EntityLivingBase entity, ItemStack armor, int slot) {
        ModelBiped armorModel = this.getSpecialArmorModel(armor, slot, entity, mainModel);
        if(armorModel != null) {
            int color;
            Item armorItem;
            Item item = armorItem = armor == null ? null : armor.getItem();
            if(armorItem instanceof ItemArmor) {
                Minecraft.getMinecraft().getTextureManager().bindTexture(RenderBiped.getArmorResource(entity, armor, slot, null));
            }
            renderer.setRenderPassModel(armorModel);
            this.setupModelForRender(armorModel, mainModel, entity);
            if(armorItem instanceof ItemArmor && (color = ((ItemArmor) armorItem).getColor(armor)) != -1) {
                float r = (color >> 16 & 0xFF) / 255.0f;
                float g = (color >> 8 & 0xFF) / 255.0f;
                float b = (color & 0xFF) / 255.0f;
                GL11.glColor3f(r, g, b);
                if(armor.isItemEnchanted()) {
                    return 31;
                }
                return 16;
            }
            GL11.glColor3f(1.0f, 1.0f, 1.0f);
            if(armor.isItemEnchanted()) {
                return 15;
            }
            return 1;
        }
        return 0;
    }

    @SubscribeEvent
    public void preRenderEntity(RenderLivingEvent.Pre event) {
        EntityLivingBase entity = event.entity;
        RendererLivingEntity renderer = event.renderer;
        if(entity instanceof EntityPlayer && renderer instanceof RenderPlayer) {
            EntityPlayer entityplayer = (EntityPlayer) entity;
            RenderPlayer renderplayer = (RenderPlayer) renderer;
            ModelBiped mainModel = renderplayer.modelBipedMain;
            ModelBiped armorModel1 = renderplayer.modelArmorChestplate;
            ModelBiped armorModel2 = renderplayer.modelArmor;
            this.setupModelForRender(mainModel, mainModel, entityplayer);
            this.setupModelForRender(armorModel1, mainModel, entityplayer);
            this.setupModelForRender(armorModel2, mainModel, entityplayer);
        }
    }

    public void setupModelForRender(ModelBiped model, ModelBiped mainModel, EntityLivingBase entity) {
        if(mainModel != null) {
            model.onGround = mainModel.onGround;
            model.isRiding = mainModel.isRiding;
            model.isChild = mainModel.isChild;
            model.isSneak = mainModel.isSneak;
        }
        else {
            model.onGround = 0.0f;
            model.isRiding = false;
            model.isChild = false;
            model.isSneak = false;
        }
        if(entity instanceof LOTREntityNPC) {
            model.bipedHeadwear.showModel = ((LOTREntityNPC) entity).shouldRenderNPCHair();
        }
        if(entity instanceof EntityPlayer) {
            ItemStack heldRight = entity == null ? null : entity.getHeldItem();
            model.aimedBow = mainModel.aimedBow;
            this.setupHeldItem(model, entity, heldRight, true);
        }
        else {
            ItemStack heldRight;
            ItemStack itemStack = heldRight = entity == null ? null : entity.getHeldItem();
            ItemStack heldLeft = entity == null ? null : (entity instanceof LOTREntityNPC ? ((LOTREntityNPC) entity).getHeldItemLeft() : null);
            this.setupHeldItem(model, entity, heldRight, true);
            this.setupHeldItem(model, entity, heldLeft, false);
        }
    }

    private void setupHeldItem(ModelBiped model, EntityLivingBase entity, ItemStack itemstack, boolean rightArm) {
        int value = 0;
        boolean aimBow = false;
        if(itemstack != null) {
            EntityPlayer entityplayer;
            value = 1;
            Item item = itemstack.getItem();
            boolean isRanged = false;

            if(itemstack.getItemUseAction() == EnumAction.bow) {
                isRanged = item instanceof LOTRItemJavelin ? entity instanceof EntityPlayer : !(item instanceof ItemSword);
            }
            if(item instanceof LOTRItemSling) {
                isRanged = true;
            }
            if(isRanged) {
                boolean aiming = model.aimedBow;
                if(entity instanceof EntityPlayer && LOTRItemCrossbow.isLoaded(itemstack)) {
                    aiming = true;
                }
                if(entity instanceof LOTREntityNPC) {
                    aiming = ((LOTREntityNPC) entity).clientCombatStance;
                }
                if(aiming) {
                    value = 3;
                    aimBow = true;
                }
            }
            if(item instanceof LOTRItemBanner) {
                value = 3;
            }
            if(entity instanceof EntityPlayer && (entityplayer = (EntityPlayer) entity).getItemInUseCount() > 0 && itemstack.getItemUseAction() == EnumAction.block) {
                value = 3;
            }
            if(entity instanceof LOTREntityNPC && ((LOTREntityNPC) entity).clientIsEating) {
                value = 3;
            }
        }
        if(rightArm) {
            model.heldItemRight = value;
            model.aimedBow = aimBow;
        }
        else {
            model.heldItemLeft = value;
        }
    }

    public void copyModelRotations(ModelBiped target, ModelBiped src) {
        this.copyBoxRotations(target.bipedHead, src.bipedHead);
        this.copyBoxRotations(target.bipedHeadwear, src.bipedHeadwear);
        this.copyBoxRotations(target.bipedBody, src.bipedBody);
        this.copyBoxRotations(target.bipedRightArm, src.bipedRightArm);
        this.copyBoxRotations(target.bipedLeftArm, src.bipedLeftArm);
        this.copyBoxRotations(target.bipedRightLeg, src.bipedRightLeg);
        this.copyBoxRotations(target.bipedLeftLeg, src.bipedLeftLeg);
    }

    public void copyBoxRotations(ModelRenderer target, ModelRenderer src) {
        target.rotationPointX = src.rotationPointX;
        target.rotationPointY = src.rotationPointY;
        target.rotationPointZ = src.rotationPointZ;
        target.rotateAngleX = src.rotateAngleX;
        target.rotateAngleY = src.rotateAngleY;
        target.rotateAngleZ = src.rotateAngleZ;
    }

    public void setupArmorForSlot(ModelBiped model, int slot) {
        model.bipedHead.showModel = slot == 0;
        model.bipedHeadwear.showModel = slot == 0;
        model.bipedBody.showModel = slot == 1 || slot == 2;
        model.bipedRightArm.showModel = slot == 1;
        model.bipedLeftArm.showModel = slot == 1;
        model.bipedRightLeg.showModel = slot == 2 || slot == 3;
        model.bipedLeftLeg.showModel = slot == 2 || slot == 3;
    }
}
