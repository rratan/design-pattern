package org.rratan.leetcode;
import java.util.Arrays;
import java.util.Base64;
import org.apache.commons.codec.binary.Base32;

public class GeoHash {

    public static String encode(double lat, double lon){

        int precision = 10;
        StringBuilder sb = new StringBuilder();
        boolean evenBits = true;
        int bits = 0;
        byte[] val = new byte[5];
        double latMin = -90, latMax = 90,lonMin = -180, lonMax = 180,mid;

        while(sb.length() < precision){
            bits++;
            if(evenBits){
                mid = (latMin+latMax)/2.0;
                if(mid>lat){
                    val[bits-1] = 0;
                    latMax = mid;
                }else{
                    val[bits-1] = 1;
                    latMin = mid;
                }
            }else{
                mid = (lonMin+lonMax)/2.0;
                if(mid>lon){
                    val[bits-1] = 0;
                    lonMin = mid;
                }else{
                    val[bits-1] = 1;
                    lonMax = mid;
                }

            }
            evenBits = !evenBits;
            if(bits == 5){
//                System.out.println((char)(val));
//                ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
//                buffer.put(val);
//                buffer.rewind();
//                int value = buffer.getInt();
//                System.out.println(value);
                sb.append(Base64.getEncoder().encode(val));
                bits=0;
              Arrays.fill(val,(byte) 0);
            }
        }
        return sb.toString();
    }

   public static void main(String[] args){
       System.out.println(encode(10,-122));
       System.out.println(Base64.getDecoder().decode(encode(10,-122)));

   }
}
