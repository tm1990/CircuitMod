package com.circuit.CircuitMod.Main;

import MiscUtils.Register.BlockRegister;
import com.circuit.CircuitMod.Blocks.DataBlocks.*;
import com.circuit.CircuitMod.Blocks.EventRecivers.*;
import com.circuit.CircuitMod.Blocks.EventSenders.*;
import com.circuit.CircuitMod.Blocks.ModBlockCableConnectionPoint;
import com.circuit.CircuitMod.Blocks.ModBlockCircuitBox;
import com.circuit.CircuitMod.Blocks.ModBlockCircuitCable;
import com.circuit.CircuitMod.Blocks.ModBlockSignalGate;
import com.circuit.CircuitMod.Items.ItemBlockCableConnectionPoint;
import com.circuit.CircuitMod.Items.ItemBlockCircuitBox;
import com.circuit.CircuitMod.Items.ItemBlockCircuitCable;
import com.circuit.CircuitMod.Items.ItemBlockLamp;
import com.circuit.CircuitMod.TileEntity.DataBlocks.*;
import com.circuit.CircuitMod.TileEntity.EventReceivers.*;
import com.circuit.CircuitMod.TileEntity.EventSenders.*;
import com.circuit.CircuitMod.TileEntity.TileEntityCableConnectionPoint;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitBox;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitCable;
import com.circuit.CircuitMod.Utils.Ref;
import net.minecraft.block.Block;

public class ModBlocks {


    public static Block CircuitBox, CircuitCable, CableConnectionPoint, Lamp;
    public static Block Variable, RandomNumberComponent;
    public static Block SignalShortender;
    public static Block DigitEquals, GreaterGate, LessGate;
    public static Block SignalGate, AdditionGate, SubtractionGate, MultiplicationGate, DividationGate;
    public static Block RedstoneReciver, RedstoneEmitter;
    public static Block OneDigitDisplay, OneDigitConstant, OneDigitCounter;
    public static Block MultiDigitDisplay, MultiDigitConstant, MultiDigitCounter;

    public static Block DataTransmitter, DataConstructor, DataReceiver, DataChatOutput, DataScreen;
    public static Block DataSelector, DataConverter, DataEncryptor, DataDecryptor;
    public static Block EntityDetector, DataDetector;
    public static Block DataDelay, PacketExtender;

    public static Block ComputerInterface;

    //TODO Add packet converter block. Allows converting a data packet with DataIntegerValue to a number packet
    //TODO Add a block which can interact with data packets through commands like if EntityName equals xxxx output signal

    //TODO Add integration with computercraft? Signal type that excecutes commands when recived by a peripheral block

    public static void RegisterBlocks(){
        BlockRegister Utils = new BlockRegister(CircuitMod.config, Ref.ModId);


        //Main blocks
        CircuitBox = new ModBlockCircuitBox().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(CircuitBox, ItemBlockCircuitBox.class, "Circuit Box", TileEntityCircuitBox.class);

        CircuitCable = new ModBlockCircuitCable().setCreativeTab(CircuitMod.CreativeTab);
        Utils.Register(CircuitCable, ItemBlockCircuitCable.class, "Circuit Cable",  TileEntityCircuitCable.class);

        CableConnectionPoint = new ModBlockCableConnectionPoint().setCreativeTab(CircuitMod.CreativeTab).setBlockTextureName(Ref.ModId.toLowerCase() + ":CableConnectionPoint");
        Utils.Register(CableConnectionPoint, ItemBlockCableConnectionPoint.class, "Cable Connection Point", TileEntityCableConnectionPoint.class);

        Lamp = new ModBlockLamp().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(Lamp, ItemBlockLamp.class, "Lamp", TileEntityLamp.class);



        //Redstone handlers
        RedstoneReciver = new ModBlockRedstoneReciver().setCreativeTab(CircuitMod.CreativeTab).setBlockTextureName(Ref.ModId.toLowerCase() + ":RedstoneEventReciver").setHardness(1F);
        Utils.Register(RedstoneReciver, "Redstone Reciver", TileEntityRedstoneReciver.class);

        RedstoneEmitter = new ModBlockRedstoneEmitter().setCreativeTab(CircuitMod.CreativeTab).setBlockTextureName(Ref.ModId.toLowerCase() + ":RedstoneEmitter").setHardness(1F);
        Utils.Register(RedstoneEmitter, "Redstone Emitter", TileEntityRedstoneEmitter.class);


        //Random things
        Variable = new ModBlockVariable().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(Variable, "Variable", TileEntityVariable.class);

        RandomNumberComponent = new ModBlockRandomNumberComponent().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(RandomNumberComponent, "Random Number Component", TileEntityRandomNumber.class);

        SignalShortender = new ModBlockSignalShortender().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(SignalShortender, "Signal Short-tender", TileEntitySignalShortender.class);


        //Gates
        DigitEquals = new ModBlockDigitEquals().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(DigitEquals, "Equals Gate", TileEntityDigitEquals.class);

        GreaterGate = new ModBlockGreaterGate().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(GreaterGate, "Greater gate", TileEntityGreaterGate.class);

        LessGate = new ModBlockLessGate().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(LessGate, "Less Gate", TileEntityLessGate.class);


        SignalGate = new ModBlockSignalGate().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(SignalGate, "Signal Gate", TileEntitySignalGate.class);

        AdditionGate = new ModBlockAddittionGate().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(AdditionGate, "Addition Gate", TileEntityAdditionGate.class);

        SubtractionGate = new ModBlockSubtractionGate().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(SubtractionGate, "Subtraction Gate", TileEntitySubtractionGate.class);

        MultiplicationGate = new ModBlockMultiplicationGate().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(MultiplicationGate, "Multiplication Gate", TileEntityMultiplicationGate.class);

        DividationGate = new ModBlockDividationGate().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(DividationGate, "Dividation Gate", TileEntityDividationGate.class);



        //One digit utils
        OneDigitDisplay = new ModBlockOneDigitDisplay().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(OneDigitDisplay,  "One-Digit Display", TileEntityOneDigitDisplay.class);

        OneDigitConstant = new ModBlockOneDigitConstant().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(OneDigitConstant, "One-Digit Constant", TileEntityOneDigitConstant.class);

        OneDigitCounter = new ModBlockOneDigitCounter().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(OneDigitCounter, "One-Digit Counter", TileEntityOneDigitCounter.class);




        //Multi digit utils
        MultiDigitDisplay = new ModBlockMultiDigitDisplay().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(MultiDigitDisplay, "Multi-Digit Display", TileEntityMultiDigitDisplay.class);

        MultiDigitConstant = new ModBlockMultiDigitConstant().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(MultiDigitConstant, "Multi-Digit Constant", TileEntityMultiDigitConstant.class);

        MultiDigitCounter = new ModBlockMultiDigitCounter().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(MultiDigitCounter, "Multi-Digit Counter", TileEntityMultiDigitCounter.class);



        DataTransmitter = new ModBlockDataTransmitter().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(DataTransmitter, "Data Transmitter", TileEntityDataTransmitter.class);

        DataConstructor = new ModBlockDataConstructor().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(DataConstructor, "Data Constructor", TileEntityDataConstructor.class);

        DataReceiver = new ModBlockDataReceiver().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(DataReceiver, "Data Receiver", TileEntityDataReceiver.class);

        DataChatOutput = new ModBlockDataChatOutput().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F).setBlockTextureName(Ref.ModId + ":DataChatOutput");
        Utils.Register(DataChatOutput, "Data Chat output", TileEntityDataChatOutput.class);

        DataSelector = new ModBlockDataSelector().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(DataSelector, "Data Selector", TileEntityDataSelector.class);

        DataConverter = new ModBlockDataConverter().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(DataConverter, "Data Converter", TileEntityDataConverter.class);

        DataScreen = new ModBlockDataScreen().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(DataScreen, "Data Screen", TileEntityDataScreen.class);


        EntityDetector = new ModBlockEntityDetector().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(EntityDetector, "Entity Detector", TileEntityEntityDetector.class);

        DataDetector = new ModBlockDataDetector().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(DataDetector, "Data Detector", TileEntityDataDetector.class);


        DataEncryptor = new ModBlockDataEncryptor().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(DataEncryptor, "Data Encryptor", TileEntityDataEncryptor.class);

        DataDecryptor = new ModBlockDataDecryptor().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(DataDecryptor, "Data Decryptor", TileEntityDataDecryptor.class);


        DataDelay = new ModBlockDataDelay().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
        Utils.Register(DataDelay, "Data Delay", TileEntityDataDelay.class);

	    PacketExtender = new ModBlockPacketExtender().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
	    Utils.Register(PacketExtender, "Packet Extender", TileEntityPacketExtender.class);




		//TODO Fix proplems with world syncing
//        if(CircuitMod.ComputerCraftLoaded){
//            ComputerInterface = new ModBlockComputerInterface().setCreativeTab(CircuitMod.CreativeTab).setHardness(1F);
//            Utils.Register(ComputerInterface, "Computer Interface", TileEntityComputerInterface.class);
//
//            PeripheralProvider.addPeripherals();
//        }



    }
}
