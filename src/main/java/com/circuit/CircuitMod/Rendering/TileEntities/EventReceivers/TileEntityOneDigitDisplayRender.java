package com.circuit.CircuitMod.Rendering.TileEntities.EventReceivers;

import com.circuit.CircuitMod.Rendering.Models.DefaultCircuitBlockModel;
import com.circuit.CircuitMod.Rendering.Models.DigitDisplayModel;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityOneDigitDisplay;
import com.circuit.CircuitMod.Utils.Ref;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class TileEntityOneDigitDisplayRender extends TileEntitySpecialRenderer {

    public final DigitDisplayModel model;
    public static ResourceLocation rs = new ResourceLocation(Ref.ModId.toLowerCase(), "textures/models/NumberDisplay.png");

    public final DefaultCircuitBlockModel model1;
    public static ResourceLocation rs1 = new ResourceLocation(Ref.ModId.toLowerCase(), "textures/models/DefaultCircuitBlockModel.png");

    public static Color Def = new Color(0,0,0);
    public static Color Border = new Color(21, 96, 30);

    public TileEntityOneDigitDisplayRender() {
        this.model = new DigitDisplayModel();
        this.model1 = new DefaultCircuitBlockModel();
    }


    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {

        if (te instanceof TileEntityOneDigitDisplay) {
            TileEntityOneDigitDisplay tile = (TileEntityOneDigitDisplay) te;

            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);


            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);


            int face = tile.Rotation == 2 ? 0 : tile.Rotation == 3 ? 2 : tile.Rotation == 4 ? 3 : tile.Rotation == 5 ? 5 : 0;

            GL11.glRotatef(((face) * 90F), 0.0F, 1.0F, 0.0F);

            boolean Num1 = false,
                    Num2 = false,
                    Num3 = false,
                    Num4 = false,
                    Num5 = false,
                    Num6 = false,
                    Num7 = false;


            int Number = tile.DisplayNumber;


            if(Number == 1){
                Num3 = true;
                Num6 = true;

            }else if(Number == 2){
                Num1 = true;
                Num2 = true;
                Num4 = true;
                Num6 = true;
                Num7 = true;

            }else if(Number == 3){
                Num1 = true;
                Num3 = true;
                Num4 = true;
                Num6 = true;
                Num7 = true;

            }else if(Number == 4){
                Num3 = true;
                Num4 = true;
                Num5 = true;
                Num6 = true;

            }else if(Number == 5){
                Num1 = true;
                Num3 = true;
                Num4 = true;
                Num5 = true;
                Num7 = true;

            }else if(Number == 6){
                Num1 = true;
                Num2 = true;
                Num3 = true;
                Num4 = true;
                Num5 = true;
                Num7 = true;

            }else if(Number == 7){
                Num7 = true;
                Num6 = true;
                Num3 = true;

            }else if(Number == 8){
                Num1 = Num2 = Num3 = Num4 = Num5 = Num6 = Num7 = true;

            }else if(Number == 9){
                Num3 = true;
                Num4 = true;
                Num5 = true;
                Num6 = true;
                Num7 = true;

            }else if(Number == 0){
                Num1 = true;
                Num2 = true;
                Num3 = true;
                Num5 = true;
                Num6 = true;
                Num7 = true;

            }

            bindTexture(rs1);
            this.model1.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, Def, Border);


            bindTexture(rs);
            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, Num1, Num2, Num3, Num4, Num5, Num6, Num7);

            GL11.glPopMatrix();
            GL11.glPopMatrix();




        }
    }


}