package GameC.Gobang.net;

public class standard {
    private static final String[] a={
        /*00*/"Correct to login",               //登陆成功（登录按钮）
        /*01*/"Error on id or password",        //登陆时账号或密码出错（登录按钮）
        /*02*/"Password too long",              //密码过长（密码设置按钮以及密码修改按钮）
        /*03*/"Used ID or SQLError",            //ID或SQL错误（注册时的ID检测）
        /*04*/"Insert Successfully",            //用户信息添加成功（注册按钮）
        /*05*/"Update Successfully",            //用户信息更新完成（用户身份信息修改与密码修改）
        /*06*/"Confirm password error",         //确认密码输入错误（密码修改处）
        /*07*/"Password modified successfully", //密码修改成功（密码修改处）
        /*08*/"Password modified failed",       //密码修改错误或SQL错误
        /*09*/"White Win",                      //白方胜利
        /*10*/"Black Win",                      //黑方胜利
        /*11*/"Continue Running",               //比赛仍在进行中
        /*12*/"regret successfully",            //悔棋成功
        /*13*/"regret Over",                    //悔棋次数已达极限
        /*14*/"Please Downzi First",            //请先下第一步棋
        /*15*/"Exit",                           //退出程序（游戏终止）
        /*16*/"Password error",                 //密码错误（出现在登录界面）
        /*17*/"______________Connected to IP::",//连接到目标IP::后边加上目标的IP
        /*18*/"Connected to IP failed",         //连接到目标IP失败
        /*19*/"Allow to begin",                 //允许启动游戏
        /*20*/"regret is ",                     //最大允许悔棋次数
        /*21*/"back information is ",           //标准信息放在后边
        /*22*/"back information Error",         //标准信息读取错误
        /*23*/"Update score successfully",      //更新分数成功
        /*24*/"Update score failed",            //更新分数失败
        /*25*/"",
        /*26*/"",
        /*27*/"",
        /*28*/"",
        /*29*/"",
        /*30*/"Loginging",                      //login状态码————登陆中（可能失败）
        /*31*/"Login Successfully",             //账号密码输入正确，登陆成功
        /*32*/"Login Failed",                   //账号或密码出错导致登陆失败
        /*33*/"registering",                    //当前IP注册状态进行中
        /*34*/"Password too long to fail",      //密码过长导致注册失败
        /*35*/"Register Successfully",          //注册成功
        /*36*/"Used ID_name to fail",           //由于用户名已被占用导致注册失败
        /*37*/"changing information",           //修改用户信息（非密码）操作执行中
        /*38*/"change successfully",            //修改用户信息（非密码）操作成功
        /*39*/"Used ID_name to change failed",  //由于被占用的用户名导致修改失败
        /*40*/"Chaning Password",               //修改用户密码信息操作执行中
        /*41*/"Correctpassword Error to fail",  //由于用户输入确认密码错误导致修改失败
        /*42*/"change password successfully",   //用户修改密码成功
        /*43*/"change password failed by SQL",  //由于服务器问题（SQL问题）导致密码修改失败
        /*44*/"regreting",                      //用户发出的悔棋申请贞子啊执行中
        /*45*/"Dowing first please",            //悔棋失败：还未落子，请先落子再进行悔棋操作
        /*46*/"regreting successfully",         //悔棋成功
        /*47*/"regreting times over",           //悔棋失败：悔棋次数已用完
        /*48*/"Exiting program————Client",      //用户结束程序
        /*49*/"Downing zi",                     //用户落子
        /*50*/"Dowing Black",                   //用户落黑子
        /*51*/"Dowing White",                   //用户落白子
        /*52*/"White part Wining",              //白方胜
        /*53*/"Black part Wining",              //黑方胜
        /*54*/"Continue",                       //尚未分出胜负，请继续
        /*55*/"Ready for begining",             //准备好了开始
        /*56*/"Get player successfully",        //成功连接到目标IP
        /*57*/"Get player failed",
        /*58*/"",
//        /*59*/"",
//        /*60*/"",
//        /*61*/"",
//        /*62*/"",
//        /*63*/"",
//        /*64*/"",
//        /*65*/"",
//        /*66*/"",
//        /*67*/"",
//        /*68*/"",
//        /*69*/"",
//        /*70*/"",
//        /*71*/"",
//        /*72*/"",
//        /*73*/"",
//        /*74*/"",
//        /*75*/"",
//        /*76*/"",
//        /*77*/"",
//        /*78*/"",
//        /*79*/"",
//        /*80*/"",
//        /*81*/"",
//        /*82*/"",
//        /*83*/"",
//        /*84*/"",
//        /*85*/"",
//        /*86*/"",
//        /*87*/"",
//        /*88*/"",
//        /*89*/"",
//        /*90*/"",
//        /*91*/"",
//        /*92*/"",
//        /*93*/"",
//        /*94*/"",
//        /*95*/"",
//        /*96*/"",
//        /*97*/"",
//        /*98*/"",
//        /*99*/""
    };
    public static String getStandard(int i){return a[i];}
}
