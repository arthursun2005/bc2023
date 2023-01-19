package sylveontest;
import battlecode.common.*;

import java.util.*;
public class Heap {

    public int[] arr;
    public int idx = 0;

    int var0 = 1_000_000;
    int var1 = 0;
    int var2 = 0;
    int var3 = 0;
    int var4 = 0;
    int var5 = 0;
    int var6 = 0;
    int var7 = 0;
    int var8 = 0;
    int var9 = 0;
    int var10 = 0;
    int var11 = 0;
    int var12 = 0;
    int var13 = 0;
    int var14 = 0;
    int var15 = 0;
    int var16 = 0;
    int var17 = 0;
    int var18 = 0;
    int var19 = 0;
    int var20 = 0;
    int var21 = 0;
    int var22 = 0;
    int var23 = 0;
    int var24 = 0;
    int var25 = 0;
    int var26 = 0;
    int var27 = 0;
    int var28 = 0;
    int var29 = 0;
    int var30 = 0;
    int var31 = 0;
    int var32 = 0;
    int var33 = 0;
    int var34 = 0;
    int var35 = 0;
    int var36 = 0;
    int var37 = 0;
    int var38 = 0;
    int var39 = 0;
    int var40 = 0;
    int var41 = 0;
    int var42 = 0;
    int var43 = 0;
    int var44 = 0;
    int var45 = 0;
    int var46 = 0;
    int var47 = 0;
    int var48 = 0;
    int var49 = 0;
    int var50 = 0;
    int var51 = 0;
    int var52 = 0;
    int var53 = 0;
    int var54 = 0;
    int var55 = 0;
    int var56 = 0;
    int var57 = 0;
    int var58 = 0;
    int var59 = 0;
    int var60 = 0;
    int var61 = 0;
    int var62 = 0;
    int var63 = 0;
    int var64 = 0;

    public Heap() {
        arr = new int[65];
        idx = 0;
        arr[0] = 1_000_000;
    }

    public void insert(int item) {



        switch (++idx) {
            case 1: {
                var1 = item;return;
            }
            case 2: {
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 3: {
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 4: {
                if (var2 <= var4) {
                    var4 = item;return;
                }
                var4 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 5: {
                if (var2 <= var5) {
                    var5 = item;return;
                }
                var5 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 6: {
                if (var3 <= var6) {
                    var6 = item;return;
                }
                var6 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 7: {
                if (var3 <= var7) {
                    var7 = item;return;
                }
                var7 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 8: {
                if (var4 <= var8) {
                    var8 = item;return;
                }
                var8 = var4;
                if (var2 <= var4) {
                    var4 = item;return;
                }
                var4 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 9: {
                if (var4 <= var9) {
                    var9 = item;return;
                }
                var9 = var4;
                if (var2 <= var4) {
                    var4 = item;return;
                }
                var4 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 10: {
                if (var5 <= var10) {
                    var10 = item;return;
                }
                var10 = var5;
                if (var2 <= var5) {
                    var5 = item;return;
                }
                var5 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 11: {
                if (var5 <= var11) {
                    var11 = item;return;
                }
                var11 = var5;
                if (var2 <= var5) {
                    var5 = item;return;
                }
                var5 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 12: {
                if (var6 <= var12) {
                    var12 = item;return;
                }
                var12 = var6;
                if (var3 <= var6) {
                    var6 = item;return;
                }
                var6 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 13: {
                if (var6 <= var13) {
                    var13 = item;return;
                }
                var13 = var6;
                if (var3 <= var6) {
                    var6 = item;return;
                }
                var6 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 14: {
                if (var7 <= var14) {
                    var14 = item;return;
                }
                var14 = var7;
                if (var3 <= var7) {
                    var7 = item;return;
                }
                var7 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 15: {
                if (var7 <= var15) {
                    var15 = item;return;
                }
                var15 = var7;
                if (var3 <= var7) {
                    var7 = item;return;
                }
                var7 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 16: {
                if (var8 <= var16) {
                    var16 = item;return;
                }
                var16 = var8;
                if (var4 <= var8) {
                    var8 = item;return;
                }
                var8 = var4;
                if (var2 <= var4) {
                    var4 = item;return;
                }
                var4 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 17: {
                if (var8 <= var17) {
                    var17 = item;return;
                }
                var17 = var8;
                if (var4 <= var8) {
                    var8 = item;return;
                }
                var8 = var4;
                if (var2 <= var4) {
                    var4 = item;return;
                }
                var4 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 18: {
                if (var9 <= var18) {
                    var18 = item;return;
                }
                var18 = var9;
                if (var4 <= var9) {
                    var9 = item;return;
                }
                var9 = var4;
                if (var2 <= var4) {
                    var4 = item;return;
                }
                var4 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 19: {
                if (var9 <= var19) {
                    var19 = item;return;
                }
                var19 = var9;
                if (var4 <= var9) {
                    var9 = item;return;
                }
                var9 = var4;
                if (var2 <= var4) {
                    var4 = item;return;
                }
                var4 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 20: {
                if (var10 <= var20) {
                    var20 = item;return;
                }
                var20 = var10;
                if (var5 <= var10) {
                    var10 = item;return;
                }
                var10 = var5;
                if (var2 <= var5) {
                    var5 = item;return;
                }
                var5 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 21: {
                if (var10 <= var21) {
                    var21 = item;return;
                }
                var21 = var10;
                if (var5 <= var10) {
                    var10 = item;return;
                }
                var10 = var5;
                if (var2 <= var5) {
                    var5 = item;return;
                }
                var5 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 22: {
                if (var11 <= var22) {
                    var22 = item;return;
                }
                var22 = var11;
                if (var5 <= var11) {
                    var11 = item;return;
                }
                var11 = var5;
                if (var2 <= var5) {
                    var5 = item;return;
                }
                var5 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 23: {
                if (var11 <= var23) {
                    var23 = item;return;
                }
                var23 = var11;
                if (var5 <= var11) {
                    var11 = item;return;
                }
                var11 = var5;
                if (var2 <= var5) {
                    var5 = item;return;
                }
                var5 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 24: {
                if (var12 <= var24) {
                    var24 = item;return;
                }
                var24 = var12;
                if (var6 <= var12) {
                    var12 = item;return;
                }
                var12 = var6;
                if (var3 <= var6) {
                    var6 = item;return;
                }
                var6 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 25: {
                if (var12 <= var25) {
                    var25 = item;return;
                }
                var25 = var12;
                if (var6 <= var12) {
                    var12 = item;return;
                }
                var12 = var6;
                if (var3 <= var6) {
                    var6 = item;return;
                }
                var6 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 26: {
                if (var13 <= var26) {
                    var26 = item;return;
                }
                var26 = var13;
                if (var6 <= var13) {
                    var13 = item;return;
                }
                var13 = var6;
                if (var3 <= var6) {
                    var6 = item;return;
                }
                var6 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 27: {
                if (var13 <= var27) {
                    var27 = item;return;
                }
                var27 = var13;
                if (var6 <= var13) {
                    var13 = item;return;
                }
                var13 = var6;
                if (var3 <= var6) {
                    var6 = item;return;
                }
                var6 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 28: {
                if (var14 <= var28) {
                    var28 = item;return;
                }
                var28 = var14;
                if (var7 <= var14) {
                    var14 = item;return;
                }
                var14 = var7;
                if (var3 <= var7) {
                    var7 = item;return;
                }
                var7 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 29: {
                if (var14 <= var29) {
                    var29 = item;return;
                }
                var29 = var14;
                if (var7 <= var14) {
                    var14 = item;return;
                }
                var14 = var7;
                if (var3 <= var7) {
                    var7 = item;return;
                }
                var7 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 30: {
                if (var15 <= var30) {
                    var30 = item;return;
                }
                var30 = var15;
                if (var7 <= var15) {
                    var15 = item;return;
                }
                var15 = var7;
                if (var3 <= var7) {
                    var7 = item;return;
                }
                var7 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 31: {
                if (var15 <= var31) {
                    var31 = item;return;
                }
                var31 = var15;
                if (var7 <= var15) {
                    var15 = item;return;
                }
                var15 = var7;
                if (var3 <= var7) {
                    var7 = item;return;
                }
                var7 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 32: {
                if (var16 <= var32) {
                    var32 = item;return;
                }
                var32 = var16;
                if (var8 <= var16) {
                    var16 = item;return;
                }
                var16 = var8;
                if (var4 <= var8) {
                    var8 = item;return;
                }
                var8 = var4;
                if (var2 <= var4) {
                    var4 = item;return;
                }
                var4 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 33: {
                if (var16 <= var33) {
                    var33 = item;return;
                }
                var33 = var16;
                if (var8 <= var16) {
                    var16 = item;return;
                }
                var16 = var8;
                if (var4 <= var8) {
                    var8 = item;return;
                }
                var8 = var4;
                if (var2 <= var4) {
                    var4 = item;return;
                }
                var4 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 34: {
                if (var17 <= var34) {
                    var34 = item;return;
                }
                var34 = var17;
                if (var8 <= var17) {
                    var17 = item;return;
                }
                var17 = var8;
                if (var4 <= var8) {
                    var8 = item;return;
                }
                var8 = var4;
                if (var2 <= var4) {
                    var4 = item;return;
                }
                var4 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 35: {
                if (var17 <= var35) {
                    var35 = item;return;
                }
                var35 = var17;
                if (var8 <= var17) {
                    var17 = item;return;
                }
                var17 = var8;
                if (var4 <= var8) {
                    var8 = item;return;
                }
                var8 = var4;
                if (var2 <= var4) {
                    var4 = item;return;
                }
                var4 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 36: {
                if (var18 <= var36) {
                    var36 = item;return;
                }
                var36 = var18;
                if (var9 <= var18) {
                    var18 = item;return;
                }
                var18 = var9;
                if (var4 <= var9) {
                    var9 = item;return;
                }
                var9 = var4;
                if (var2 <= var4) {
                    var4 = item;return;
                }
                var4 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 37: {
                if (var18 <= var37) {
                    var37 = item;return;
                }
                var37 = var18;
                if (var9 <= var18) {
                    var18 = item;return;
                }
                var18 = var9;
                if (var4 <= var9) {
                    var9 = item;return;
                }
                var9 = var4;
                if (var2 <= var4) {
                    var4 = item;return;
                }
                var4 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 38: {
                if (var19 <= var38) {
                    var38 = item;return;
                }
                var38 = var19;
                if (var9 <= var19) {
                    var19 = item;return;
                }
                var19 = var9;
                if (var4 <= var9) {
                    var9 = item;return;
                }
                var9 = var4;
                if (var2 <= var4) {
                    var4 = item;return;
                }
                var4 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 39: {
                if (var19 <= var39) {
                    var39 = item;return;
                }
                var39 = var19;
                if (var9 <= var19) {
                    var19 = item;return;
                }
                var19 = var9;
                if (var4 <= var9) {
                    var9 = item;return;
                }
                var9 = var4;
                if (var2 <= var4) {
                    var4 = item;return;
                }
                var4 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 40: {
                if (var20 <= var40) {
                    var40 = item;return;
                }
                var40 = var20;
                if (var10 <= var20) {
                    var20 = item;return;
                }
                var20 = var10;
                if (var5 <= var10) {
                    var10 = item;return;
                }
                var10 = var5;
                if (var2 <= var5) {
                    var5 = item;return;
                }
                var5 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 41: {
                if (var20 <= var41) {
                    var41 = item;return;
                }
                var41 = var20;
                if (var10 <= var20) {
                    var20 = item;return;
                }
                var20 = var10;
                if (var5 <= var10) {
                    var10 = item;return;
                }
                var10 = var5;
                if (var2 <= var5) {
                    var5 = item;return;
                }
                var5 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 42: {
                if (var21 <= var42) {
                    var42 = item;return;
                }
                var42 = var21;
                if (var10 <= var21) {
                    var21 = item;return;
                }
                var21 = var10;
                if (var5 <= var10) {
                    var10 = item;return;
                }
                var10 = var5;
                if (var2 <= var5) {
                    var5 = item;return;
                }
                var5 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 43: {
                if (var21 <= var43) {
                    var43 = item;return;
                }
                var43 = var21;
                if (var10 <= var21) {
                    var21 = item;return;
                }
                var21 = var10;
                if (var5 <= var10) {
                    var10 = item;return;
                }
                var10 = var5;
                if (var2 <= var5) {
                    var5 = item;return;
                }
                var5 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 44: {
                if (var22 <= var44) {
                    var44 = item;return;
                }
                var44 = var22;
                if (var11 <= var22) {
                    var22 = item;return;
                }
                var22 = var11;
                if (var5 <= var11) {
                    var11 = item;return;
                }
                var11 = var5;
                if (var2 <= var5) {
                    var5 = item;return;
                }
                var5 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 45: {
                if (var22 <= var45) {
                    var45 = item;return;
                }
                var45 = var22;
                if (var11 <= var22) {
                    var22 = item;return;
                }
                var22 = var11;
                if (var5 <= var11) {
                    var11 = item;return;
                }
                var11 = var5;
                if (var2 <= var5) {
                    var5 = item;return;
                }
                var5 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 46: {
                if (var23 <= var46) {
                    var46 = item;return;
                }
                var46 = var23;
                if (var11 <= var23) {
                    var23 = item;return;
                }
                var23 = var11;
                if (var5 <= var11) {
                    var11 = item;return;
                }
                var11 = var5;
                if (var2 <= var5) {
                    var5 = item;return;
                }
                var5 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 47: {
                if (var23 <= var47) {
                    var47 = item;return;
                }
                var47 = var23;
                if (var11 <= var23) {
                    var23 = item;return;
                }
                var23 = var11;
                if (var5 <= var11) {
                    var11 = item;return;
                }
                var11 = var5;
                if (var2 <= var5) {
                    var5 = item;return;
                }
                var5 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
            case 48: {
                if (var24 <= var48) {
                    var48 = item;return;
                }
                var48 = var24;
                if (var12 <= var24) {
                    var24 = item;return;
                }
                var24 = var12;
                if (var6 <= var12) {
                    var12 = item;return;
                }
                var12 = var6;
                if (var3 <= var6) {
                    var6 = item;return;
                }
                var6 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 49: {
                if (var24 <= var49) {
                    var49 = item;return;
                }
                var49 = var24;
                if (var12 <= var24) {
                    var24 = item;return;
                }
                var24 = var12;
                if (var6 <= var12) {
                    var12 = item;return;
                }
                var12 = var6;
                if (var3 <= var6) {
                    var6 = item;return;
                }
                var6 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 50: {
                if (var25 <= var50) {
                    var50 = item;return;
                }
                var50 = var25;
                if (var12 <= var25) {
                    var25 = item;return;
                }
                var25 = var12;
                if (var6 <= var12) {
                    var12 = item;return;
                }
                var12 = var6;
                if (var3 <= var6) {
                    var6 = item;return;
                }
                var6 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 51: {
                if (var25 <= var51) {
                    var51 = item;return;
                }
                var51 = var25;
                if (var12 <= var25) {
                    var25 = item;return;
                }
                var25 = var12;
                if (var6 <= var12) {
                    var12 = item;return;
                }
                var12 = var6;
                if (var3 <= var6) {
                    var6 = item;return;
                }
                var6 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 52: {
                if (var26 <= var52) {
                    var52 = item;return;
                }
                var52 = var26;
                if (var13 <= var26) {
                    var26 = item;return;
                }
                var26 = var13;
                if (var6 <= var13) {
                    var13 = item;return;
                }
                var13 = var6;
                if (var3 <= var6) {
                    var6 = item;return;
                }
                var6 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 53: {
                if (var26 <= var53) {
                    var53 = item;return;
                }
                var53 = var26;
                if (var13 <= var26) {
                    var26 = item;return;
                }
                var26 = var13;
                if (var6 <= var13) {
                    var13 = item;return;
                }
                var13 = var6;
                if (var3 <= var6) {
                    var6 = item;return;
                }
                var6 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 54: {
                if (var27 <= var54) {
                    var54 = item;return;
                }
                var54 = var27;
                if (var13 <= var27) {
                    var27 = item;return;
                }
                var27 = var13;
                if (var6 <= var13) {
                    var13 = item;return;
                }
                var13 = var6;
                if (var3 <= var6) {
                    var6 = item;return;
                }
                var6 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 55: {
                if (var27 <= var55) {
                    var55 = item;return;
                }
                var55 = var27;
                if (var13 <= var27) {
                    var27 = item;return;
                }
                var27 = var13;
                if (var6 <= var13) {
                    var13 = item;return;
                }
                var13 = var6;
                if (var3 <= var6) {
                    var6 = item;return;
                }
                var6 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 56: {
                if (var28 <= var56) {
                    var56 = item;return;
                }
                var56 = var28;
                if (var14 <= var28) {
                    var28 = item;return;
                }
                var28 = var14;
                if (var7 <= var14) {
                    var14 = item;return;
                }
                var14 = var7;
                if (var3 <= var7) {
                    var7 = item;return;
                }
                var7 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 57: {
                if (var28 <= var57) {
                    var57 = item;return;
                }
                var57 = var28;
                if (var14 <= var28) {
                    var28 = item;return;
                }
                var28 = var14;
                if (var7 <= var14) {
                    var14 = item;return;
                }
                var14 = var7;
                if (var3 <= var7) {
                    var7 = item;return;
                }
                var7 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 58: {
                if (var29 <= var58) {
                    var58 = item;return;
                }
                var58 = var29;
                if (var14 <= var29) {
                    var29 = item;return;
                }
                var29 = var14;
                if (var7 <= var14) {
                    var14 = item;return;
                }
                var14 = var7;
                if (var3 <= var7) {
                    var7 = item;return;
                }
                var7 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 59: {
                if (var29 <= var59) {
                    var59 = item;return;
                }
                var59 = var29;
                if (var14 <= var29) {
                    var29 = item;return;
                }
                var29 = var14;
                if (var7 <= var14) {
                    var14 = item;return;
                }
                var14 = var7;
                if (var3 <= var7) {
                    var7 = item;return;
                }
                var7 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 60: {
                if (var30 <= var60) {
                    var60 = item;return;
                }
                var60 = var30;
                if (var15 <= var30) {
                    var30 = item;return;
                }
                var30 = var15;
                if (var7 <= var15) {
                    var15 = item;return;
                }
                var15 = var7;
                if (var3 <= var7) {
                    var7 = item;return;
                }
                var7 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 61: {
                if (var30 <= var61) {
                    var61 = item;return;
                }
                var61 = var30;
                if (var15 <= var30) {
                    var30 = item;return;
                }
                var30 = var15;
                if (var7 <= var15) {
                    var15 = item;return;
                }
                var15 = var7;
                if (var3 <= var7) {
                    var7 = item;return;
                }
                var7 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 62: {
                if (var31 <= var62) {
                    var62 = item;return;
                }
                var62 = var31;
                if (var15 <= var31) {
                    var31 = item;return;
                }
                var31 = var15;
                if (var7 <= var15) {
                    var15 = item;return;
                }
                var15 = var7;
                if (var3 <= var7) {
                    var7 = item;return;
                }
                var7 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 63: {
                if (var31 <= var63) {
                    var63 = item;return;
                }
                var63 = var31;
                if (var15 <= var31) {
                    var31 = item;return;
                }
                var31 = var15;
                if (var7 <= var15) {
                    var15 = item;return;
                }
                var15 = var7;
                if (var3 <= var7) {
                    var7 = item;return;
                }
                var7 = var3;
                if (var1 <= var3) {
                    var3 = item;return;
                }
                var3 = var1;
                var1 = item;return;
            }
            case 64: {
                if (var32 <= var64) {
                    var64 = item;return;
                }
                var64 = var32;
                if (var16 <= var32) {
                    var32 = item;return;
                }
                var32 = var16;
                if (var8 <= var16) {
                    var16 = item;return;
                }
                var16 = var8;
                if (var4 <= var8) {
                    var8 = item;return;
                }
                var8 = var4;
                if (var2 <= var4) {
                    var4 = item;return;
                }
                var4 = var2;
                if (var1 <= var2) {
                    var2 = item;return;
                }
                var2 = var1;
                var1 = item;return;
            }
        }
    }

    // TODO OPTIMISE POP

}
