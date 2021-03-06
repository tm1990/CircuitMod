package com.circuit.CircuitMod.Rendering.TileEntities.EventReceivers;

import com.circuit.CircuitMod.Rendering.Models.DefaultCircuitBlockModel;
import com.circuit.CircuitMod.Rendering.Models.DigitDisplayModel;
import com.circuit.CircuitMod.TileEntity.EventReceivers.TileEntityMultiDigitDisplay;
import com.circuit.CircuitMod.Utils.Ref;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class TIleEntityMultiDigitDisplayRender  extends TileEntitySpecialRenderer {

    public final DigitDisplayModel model;
    public static ResourceLocation rs = new ResourceLocation(Ref.ModId.toLowerCase(), "textures/models/NumberDisplay.png");

    public final DefaultCircuitBlockModel model1;
    public static ResourceLocation rs1 = new ResourceLocation(Ref.ModId.toLowerCase(), "textures/models/DefaultCircuitBlockModel.png");

    public static Color Def = new Color(0,0,0);
    public static Color Border = new Color(96, 8, 14);

    public TIleEntityMultiDigitDisplayRender() {
        this.model = new DigitDisplayModel();
        this.model1 = new DefaultCircuitBlockModel();
    }


    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float sc) {

        if (te instanceof TileEntityMultiDigitDisplay) {
            TileEntityMultiDigitDisplay tile = (TileEntityMultiDigitDisplay) te;

            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);


            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);


            int face = tile.Rotation == 2 ? 0 : tile.Rotation == 3 ? 2 : tile.Rotation == 4 ? 3 : tile.Rotation == 5 ? 5 : 0;

            GL11.glRotatef(((face) * 90F), 0.0F, 1.0F, 0.0F);

            bindTexture(rs1);
            this.model1.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, Def, Border);


            int Number = tile.DisplayNumber;

            if(Number >= 9999)
                Number = 9999;


            if(!(Integer.toString(Number)).isEmpty()) {
                String nums = Integer.toString(Number);
                if(nums.length() < 4){
                    int t = 4 - nums.length();

                    for(int i = 0; i < t; i++){
                        nums = "X" + nums;
                    }
                }

                String[] Numbers = nums.split("");
                int Tens = nums.length();

                bindTexture(rs);


                float scale = 0.36F;
                float offset = 0.6F;


                GL11.glPushMatrix();

                GL11.glTranslatef(-(offset/1.85F), 0.61F, -0.22F);
                GL11.glScalef(scale, scale, 0.5F);

                for(int i = 0; i < 4; i++){
                    if(Numbers.length > i && Number != -1){

                        if(Numbers[i].equalsIgnoreCase("X")){

                            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, false, false, false, false, false, false, false);
                        }else {

                            Integer tempg = Integer.decode(Numbers[i]);
                            boolean[] Num = GetStateForNumber(tempg);


                            if (Num != null) {
                                this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, Num[0], Num[1], Num[2], Num[3], Num[4], Num[5], Num[6]);
                            }

                        }
                    }else{
                            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, false, false, false, false, false, false, false);
                        }

                    GL11.glTranslatef(offset, 0, 0);

                }


            }

            GL11.glPopMatrix();
            GL11.glPopMatrix();
            GL11.glPopMatrix();




        }

    }

    public static boolean[] GetStateForNumber(Integer Number){
        boolean[] Num = new boolean[7];


        if(Number == null)
            return null;

        if(Number == 1){
            Num[2] = true;
            Num[5] = true;

        }else if(Number == 2){
            Num[0] = true;
            Num[1] = true;
            Num[3] = true;
            Num[5] = true;
            Num[6] = true;

        }else if(Number == 3){
            Num[0] = true;
            Num[2] = true;
            Num[3] = true;
            Num[5] = true;
            Num[6] = true;

        }else if(Number == 4){
            Num[2] = true;
            Num[3] = true;
            Num[4] = true;
            Num[5] = true;

        }else if(Number == 5){
            Num[0] = true;
            Num[2] = true;
            Num[3] = true;
            Num[4] = true;
            Num[6] = true;

        }else if(Number == 6){
            Num[0] = true;
            Num[1] = true;
            Num[2] = true;
            Num[3] = true;
            Num[4] = true;
            Num[6] = true;

        }else if(Number == 7){
            Num[6] = true;
            Num[5] = true;
            Num[2] = true;

        }else if(Number == 8){
            Num[0] = true;
            Num[1] = true;
            Num[2] = true;
            Num[3] = true;
            Num[4] = true;
            Num[5] = true;
            Num[6] = true;

        }else if(Number == 9){
            Num[2] = true;
            Num[3] = true;
            Num[4] = true;
            Num[5] = true;
            Num[6] = true;

        }else if(Number == 0){
            Num[0] = true;
            Num[1] = true;
            Num[2] = true;
            Num[4] = true;
            Num[5] = true;
            Num[6] = true;

        }


        return Num;
    }


}