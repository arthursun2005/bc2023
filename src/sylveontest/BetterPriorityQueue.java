package sylveontest;

import battlecode.common.Clock;

public class BetterPriorityQueue {

    int queue[] = new int[1000];
    public BetterPriorityQueue() {

    }

    static int lastdist = 0;
    static int head0;
    static int tail0;
    static int head1;
    static int tail1;
    static int head2;
    static int tail2;
    static int head3;
    static int tail3;
    static int head4;
    static int tail4;
    static int head5;
    static int tail5;
    static int head6;
    static int tail6;
    static int head7;
    static int tail7;
    static int head8;
    static int tail8;
    static int head9;
    static int tail9;
    static int head10;
    static int tail10;
    static int head11;
    static int tail11;
    static int head12;
    static int tail12;
    static int head13;
    static int tail13;
    static int head14;
    static int tail14;
    static int head15;
    static int tail15;
    static int head16;
    static int tail16;
    static int head17;
    static int tail17;
    static int head18;
    static int tail18;
    static int head19;
    static int tail19;
    static int head20;
    static int tail20;
    static int head21;
    static int tail21;
    static int head22;
    static int tail22;
    static int head23;
    static int tail23;
    static int head24;
    static int tail24;
    static int head25;
    static int tail25;
    static int head26;
    static int tail26;
    static int head27;
    static int tail27;
    static int head28;
    static int tail28;
    static int head29;
    static int tail29;
    static int head30;
    static int tail30;
    static int head31;
    static int tail31;
    static int head32;
    static int tail32;
    static int head33;
    static int tail33;
    static int head34;
    static int tail34;
    static int head35;
    static int tail35;
    static int head36;
    static int tail36;
    static int head37;
    static int tail37;
    static int head38;
    static int tail38;
    static int head39;
    static int tail39;
    static int head40;
    static int tail40;
    static int head41;
    static int tail41;
    static int head42;
    static int tail42;
    static int head43;
    static int tail43;
    static int head44;
    static int tail44;
    static int head45;
    static int tail45;
    static int head46;
    static int tail46;
    static int head47;
    static int tail47;
    static int head48;
    static int tail48;
    static int head49;
    static int tail49;
    static int head50;
    static int tail50;
    static int head51;
    static int tail51;
    static int head52;
    static int tail52;
    static int head53;
    static int tail53;
    static int head54;
    static int tail54;
    static int head55;
    static int tail55;
    static int head56;
    static int tail56;
    static int head57;
    static int tail57;
    static int head58;
    static int tail58;
    static int head59;
    static int tail59;
    static int head60;
    static int tail60;
    static int head61;
    static int tail61;
    static int head62;
    static int tail62;
    static int head63;
    static int tail63;
    static int head64;
    static int tail64;
    static int head65;
    static int tail65;
    static int head66;
    static int tail66;
    static int head67;
    static int tail67;
    static int head68;
    static int tail68;
    static int head69;
    static int tail69;
    void reset() {
        head0 = 0;
        tail0 = 0;
        head1 = 10;
        tail1 = 10;
        head2 = 20;
        tail2 = 20;
        head3 = 30;
        tail3 = 30;
        head4 = 40;
        tail4 = 40;
        head5 = 50;
        tail5 = 50;
        head6 = 60;
        tail6 = 60;
        head7 = 70;
        tail7 = 70;
        head8 = 80;
        tail8 = 80;
        head9 = 90;
        tail9 = 90;
        head10 = 100;
        tail10 = 100;
        head11 = 110;
        tail11 = 110;
        head12 = 120;
        tail12 = 120;
        head13 = 130;
        tail13 = 130;
        head14 = 140;
        tail14 = 140;
        head15 = 150;
        tail15 = 150;
        head16 = 160;
        tail16 = 160;
        head17 = 170;
        tail17 = 170;
        head18 = 180;
        tail18 = 180;
        head19 = 190;
        tail19 = 190;
        head20 = 200;
        tail20 = 200;
        head21 = 210;
        tail21 = 210;
        head22 = 220;
        tail22 = 220;
        head23 = 230;
        tail23 = 230;
        head24 = 240;
        tail24 = 240;
        head25 = 250;
        tail25 = 250;
        head26 = 260;
        tail26 = 260;
        head27 = 270;
        tail27 = 270;
        head28 = 280;
        tail28 = 280;
        head29 = 290;
        tail29 = 290;
        head30 = 300;
        tail30 = 300;
        head31 = 310;
        tail31 = 310;
        head32 = 320;
        tail32 = 320;
        head33 = 330;
        tail33 = 330;
        head34 = 340;
        tail34 = 340;
        head35 = 350;
        tail35 = 350;
        head36 = 360;
        tail36 = 360;
        head37 = 370;
        tail37 = 370;
        head38 = 380;
        tail38 = 380;
        head39 = 390;
        tail39 = 390;
        head40 = 400;
        tail40 = 400;
        head41 = 410;
        tail41 = 410;
        head42 = 420;
        tail42 = 420;
        head43 = 430;
        tail43 = 430;
        head44 = 440;
        tail44 = 440;
        head45 = 450;
        tail45 = 450;
        head46 = 460;
        tail46 = 460;
        head47 = 470;
        tail47 = 470;
        head48 = 480;
        tail48 = 480;
        head49 = 490;
        tail49 = 490;
        head50 = 500;
        tail50 = 500;
        head51 = 510;
        tail51 = 510;
        head52 = 520;
        tail52 = 520;
        head53 = 530;
        tail53 = 530;
        head54 = 540;
        tail54 = 540;
        head55 = 550;
        tail55 = 550;
        head56 = 560;
        tail56 = 560;
        head57 = 570;
        tail57 = 570;
        head58 = 580;
        tail58 = 580;
        head59 = 590;
        tail59 = 590;
        head60 = 600;
        tail60 = 600;
        head61 = 610;
        tail61 = 610;
        head62 = 620;
        tail62 = 620;
        head63 = 630;
        tail63 = 630;
        head64 = 640;
        tail64 = 640;
        head65 = 650;
        tail65 = 650;
        head66 = 660;
        tail66 = 660;
        head67 = 670;
        tail67 = 670;
        head68 = 680;
        tail68 = 680;
        head69 = 690;
        tail69 = 690;

        lastdist = 0;
    }
    public void insert(int dist, int loc) {
        switch(dist) {
            case 0:
                queue[tail0++] = loc;
                break;
            case 1:
                queue[tail1++] = loc;
                break;
            case 2:
                queue[tail2++] = loc;
                break;
            case 3:
                queue[tail3++] = loc;
                break;
            case 4:
                queue[tail4++] = loc;
                break;
            case 5:
                queue[tail5++] = loc;
                break;
            case 6:
                queue[tail6++] = loc;
                break;
            case 7:
                queue[tail7++] = loc;
                break;
            case 8:
                queue[tail8++] = loc;
                break;
            case 9:
                queue[tail9++] = loc;
                break;
            case 10:
                queue[tail10++] = loc;
                break;
            case 11:
                queue[tail11++] = loc;
                break;
            case 12:
                queue[tail12++] = loc;
                break;
            case 13:
                queue[tail13++] = loc;
                break;
            case 14:
                queue[tail14++] = loc;
                break;
            case 15:
                queue[tail15++] = loc;
                break;
            case 16:
                queue[tail16++] = loc;
                break;
            case 17:
                queue[tail17++] = loc;
                break;
            case 18:
                queue[tail18++] = loc;
                break;
            case 19:
                queue[tail19++] = loc;
                break;
            case 20:
                queue[tail20++] = loc;
                break;
            case 21:
                queue[tail21++] = loc;
                break;
            case 22:
                queue[tail22++] = loc;
                break;
            case 23:
                queue[tail23++] = loc;
                break;
            case 24:
                queue[tail24++] = loc;
                break;
            case 25:
                queue[tail25++] = loc;
                break;
            case 26:
                queue[tail26++] = loc;
                break;
            case 27:
                queue[tail27++] = loc;
                break;
            case 28:
                queue[tail28++] = loc;
                break;
            case 29:
                queue[tail29++] = loc;
                break;
            case 30:
                queue[tail30++] = loc;
                break;
            case 31:
                queue[tail31++] = loc;
                break;
            case 32:
                queue[tail32++] = loc;
                break;
            case 33:
                queue[tail33++] = loc;
                break;
            case 34:
                queue[tail34++] = loc;
                break;
            case 35:
                queue[tail35++] = loc;
                break;
            case 36:
                queue[tail36++] = loc;
                break;
            case 37:
                queue[tail37++] = loc;
                break;
            case 38:
                queue[tail38++] = loc;
                break;
            case 39:
                queue[tail39++] = loc;
                break;
            case 40:
                queue[tail40++] = loc;
                break;
            case 41:
                queue[tail41++] = loc;
                break;
            case 42:
                queue[tail42++] = loc;
                break;
            case 43:
                queue[tail43++] = loc;
                break;
            case 44:
                queue[tail44++] = loc;
                break;
            case 45:
                queue[tail45++] = loc;
                break;
            case 46:
                queue[tail46++] = loc;
                break;
            case 47:
                queue[tail47++] = loc;
                break;
            case 48:
                queue[tail48++] = loc;
                break;
            case 49:
                queue[tail49++] = loc;
                break;
            case 50:
                queue[tail50++] = loc;
                break;
            case 51:
                queue[tail51++] = loc;
                break;
            case 52:
                queue[tail52++] = loc;
                break;
            case 53:
                queue[tail53++] = loc;
                break;
            case 54:
                queue[tail54++] = loc;
                break;
            case 55:
                queue[tail55++] = loc;
                break;
            case 56:
                queue[tail56++] = loc;
                break;
            case 57:
                queue[tail57++] = loc;
                break;
            case 58:
                queue[tail58++] = loc;
                break;
            case 59:
                queue[tail59++] = loc;
                break;
            case 60:
                queue[tail60++] = loc;
                break;
            case 61:
                queue[tail61++] = loc;
                break;
            case 62:
                queue[tail62++] = loc;
                break;
            case 63:
                queue[tail63++] = loc;
                break;
            case 64:
                queue[tail64++] = loc;
                break;
            case 65:
                queue[tail65++] = loc;
                break;
            case 66:
                queue[tail66++] = loc;
                break;
            case 67:
                queue[tail67++] = loc;
                break;
            case 68:
                queue[tail68++] = loc;
                break;
            case 69:
                queue[tail69++] = loc;
                break;
        }
    }

    public int pop() {
//            System.out.println(stb);
        for (; lastdist < 70; lastdist++) {
            System.out.println("confusion: " + Clock.getBytecodesLeft());
            switch(lastdist) {
                case 0:
                    if (head0 == tail0) break;
                    return queue[head0++];
                case 1:
                    if (head1 == tail1) break;
                    return queue[head1++];
                case 2:
                    if (head2 == tail2) break;
                    return queue[head2++];
                case 3:
                    if (head3 == tail3) break;
                    return queue[head3++];
                case 4:
                    if (head4 == tail4) break;
                    return queue[head4++];
                case 5:
                    if (head5 == tail5) break;
                    return queue[head5++];
                case 6:
                    if (head6 == tail6) break;
                    return queue[head6++];
                case 7:
                    if (head7 == tail7) break;
                    return queue[head7++];
                case 8:
                    if (head8 == tail8) break;
                    return queue[head8++];
                case 9:
                    if (head9 == tail9) break;
                    return queue[head9++];
                case 10:
                    if (head10 == tail10) break;
                    return queue[head10++];
                case 11:
                    if (head11 == tail11) break;
                    return queue[head11++];
                case 12:
                    if (head12 == tail12) break;
                    return queue[head12++];
                case 13:
                    if (head13 == tail13) break;
                    return queue[head13++];
                case 14:
                    if (head14 == tail14) break;
                    return queue[head14++];
                case 15:
                    if (head15 == tail15) break;
                    return queue[head15++];
                case 16:
                    if (head16 == tail16) break;
                    return queue[head16++];
                case 17:
                    if (head17 == tail17) break;
                    return queue[head17++];
                case 18:
                    if (head18 == tail18) break;
                    return queue[head18++];
                case 19:
                    if (head19 == tail19) break;
                    return queue[head19++];
                case 20:
                    if (head20 == tail20) break;
                    return queue[head20++];
                case 21:
                    if (head21 == tail21) break;
                    return queue[head21++];
                case 22:
                    if (head22 == tail22) break;
                    return queue[head22++];
                case 23:
                    if (head23 == tail23) break;
                    return queue[head23++];
                case 24:
                    if (head24 == tail24) break;
                    return queue[head24++];
                case 25:
                    if (head25 == tail25) break;
                    return queue[head25++];
                case 26:
                    if (head26 == tail26) break;
                    return queue[head26++];
                case 27:
                    if (head27 == tail27) break;
                    return queue[head27++];
                case 28:
                    if (head28 == tail28) break;
                    return queue[head28++];
                case 29:
                    if (head29 == tail29) break;
                    return queue[head29++];
                case 30:
                    if (head30 == tail30) break;
                    return queue[head30++];
                case 31:
                    if (head31 == tail31) break;
                    return queue[head31++];
                case 32:
                    if (head32 == tail32) break;
                    return queue[head32++];
                case 33:
                    if (head33 == tail33) break;
                    return queue[head33++];
                case 34:
                    if (head34 == tail34) break;
                    return queue[head34++];
                case 35:
                    if (head35 == tail35) break;
                    return queue[head35++];
                case 36:
                    if (head36 == tail36) break;
                    return queue[head36++];
                case 37:
                    if (head37 == tail37) break;
                    return queue[head37++];
                case 38:
                    if (head38 == tail38) break;
                    return queue[head38++];
                case 39:
                    if (head39 == tail39) break;
                    return queue[head39++];
                case 40:
                    if (head40 == tail40) break;
                    return queue[head40++];
                case 41:
                    if (head41 == tail41) break;
                    return queue[head41++];
                case 42:
                    if (head42 == tail42) break;
                    return queue[head42++];
                case 43:
                    if (head43 == tail43) break;
                    return queue[head43++];
                case 44:
                    if (head44 == tail44) break;
                    return queue[head44++];
                case 45:
                    if (head45 == tail45) break;
                    return queue[head45++];
                case 46:
                    if (head46 == tail46) break;
                    return queue[head46++];
                case 47:
                    if (head47 == tail47) break;
                    return queue[head47++];
                case 48:
                    if (head48 == tail48) break;
                    return queue[head48++];
                case 49:
                    if (head49 == tail49) break;
                    return queue[head49++];
                case 50:
                    if (head50 == tail50) break;
                    return queue[head50++];
                case 51:
                    if (head51 == tail51) break;
                    return queue[head51++];
                case 52:
                    if (head52 == tail52) break;
                    return queue[head52++];
                case 53:
                    if (head53 == tail53) break;
                    return queue[head53++];
                case 54:
                    if (head54 == tail54) break;
                    return queue[head54++];
                case 55:
                    if (head55 == tail55) break;
                    return queue[head55++];
                case 56:
                    if (head56 == tail56) break;
                    return queue[head56++];
                case 57:
                    if (head57 == tail57) break;
                    return queue[head57++];
                case 58:
                    if (head58 == tail58) break;
                    return queue[head58++];
                case 59:
                    if (head59 == tail59) break;
                    return queue[head59++];
                case 60:
                    if (head60 == tail60) break;
                    return queue[head60++];
                case 61:
                    if (head61 == tail61) break;
                    return queue[head61++];
                case 62:
                    if (head62 == tail62) break;
                    return queue[head62++];
                case 63:
                    if (head63 == tail63) break;
                    return queue[head63++];
                case 64:
                    if (head64 == tail64) break;
                    return queue[head64++];
                case 65:
                    if (head65 == tail65) break;
                    return queue[head65++];
                case 66:
                    if (head66 == tail66) break;
                    return queue[head66++];
                case 67:
                    if (head67 == tail67) break;
                    return queue[head67++];
                case 68:
                    if (head68 == tail68) break;
                    return queue[head68++];
                case 69:
                    if (head69 == tail69) break;
                    return queue[head69++];
            }
            System.out.println("confusion end: " + Clock.getBytecodesLeft());

        }

        return -1;
    }
}