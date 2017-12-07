package com.socket.echo.coder;

import com.socket.echo.bean.DeviceMsg;
import com.socket.echo.util.Utils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

public class MsgDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        /*int length = byteBuf.readableBytes();
        byte[] bytes = new byte[4];

        byteBuf.getBytes(36,bytes,0,4);
        String temp = new String(bytes,"UTF-8");
//        list.add(temp);
        list.add(temp);//此处list有几个对象，channelRead0就会执行几次*/

        //        byte[] byte11=new byte[]{123,9,0,25,49,51,57,49,50,51,52,53,54,55,56,1,3,4,2,-22,0,-94,91,-58,123};

        int length = byteBuf.readableBytes();
        byte[] bytes = new byte[length];

        byteBuf.getBytes(byteBuf.readerIndex(),bytes,0,length);

        String result = Utils.bytesToHexString(bytes);


        DeviceMsg deviceMsg = new DeviceMsg();

        String msgType = result.substring(2,4);
        String temperature = "";
        String humidity = "";
        String waterMeter = "";

        if ("01".equals(msgType)){//登录
            deviceMsg.setMsgType(1);

        }else{
            msgType = result.substring(6,8);

            if ("19".equals(msgType)){//温湿度
                humidity = result.substring(36,40);//湿度
                temperature = result.substring(40,44);//温度

                /*String binStr = Utils.hexStr2BinStr(temperature);
                //判断是否为正数
                boolean flag = binStr.substring(0,1).equals("0") ? true : false;
                if (flag){
                    deviceMsg.setTemperature(Integer.parseInt(temperature,16));
                }else {
                    StringBuffer stringBuffer = new StringBuffer("");
                    for (int i = 0;i<binStr.length();i++){
                        String temp = binStr.substring(i,i+1);
                        stringBuffer.append(temp.equals("0") ? "1" : "0");
                    }
                    int tempe = Integer.parseInt(stringBuffer.toString(),2);
                    deviceMsg.setTemperature(tempe);
                }*/

                byte[] binBytes = Utils.hexStr2BinArr(temperature);
                boolean flag = binBytes[0] == 0 ? true : false;
                if (flag){
                    deviceMsg.setTemperature(Integer.parseInt(temperature,16));
                }else {
                    deviceMsg.setTemperature(binBytes[1]);
                }

                deviceMsg.setMsgType(2);
                deviceMsg.setHumidity(Integer.parseInt(humidity,16));

            }else if ("1D".equals(msgType)){//水表
                waterMeter = result.substring(36,44);

                deviceMsg.setMsgType(3);

                DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");//格式化设置

                String meter = new BigDecimal(Integer.parseInt(waterMeter)).divide(new BigDecimal(10000)).toString();
                deviceMsg.setWaterMeter(meter);

            }
        }

        list.add(deviceMsg);
    }
}
