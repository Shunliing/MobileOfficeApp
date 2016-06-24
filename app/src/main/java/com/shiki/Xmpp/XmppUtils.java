package com.shiki.Xmpp;

import com.orhanobut.logger.Logger;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric on 2016/6/22.
 */
public class XmppUtils {
    private static final String SERVER_NAME = "139.196.153.245";
    private static final String SERVER_HOST = "139.196.153.245";
    private static final int SERVER_PORT = 5222;
    private static final String RESOURCE = "SSE";
    private static XmppUtils mInstance;
    private AbstractXMPPConnection mConnection = null;
    private Map<String, Chat> mChatMap = new HashMap<String, Chat>();// 聊天窗口管理map集合
    /*private static XmppConnection xmppConnection = new XmppConnection();
    private TaxiConnectionListener connectionListener;*/

    private XmppUtils() {
        XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                //服务器名称
                .setServiceName(SERVER_NAME)
                //服务器IP地址
                .setHost(SERVER_HOST)
                //服务器端口
                .setPort(SERVER_PORT)
                //是否开启压缩
                .setCompressionEnabled(false)
                //是否开启安全模式
                .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                //开启调试模式
                .setDebuggerEnabled(true)
                //不告诉服务器自己的状态,方便接收离线消息
                //.setSendPresence(false)
                .build();
        mConnection = new XMPPTCPConnection(config);
        try {
            mConnection.connect();
            mConnection.addConnectionListener(mConnectionListener);
            ChatManager chatManager = ChatManager.getInstanceFor(mConnection);
            chatManager.addChatListener(new ChatManagerListener() {
                @Override
                public void chatCreated(Chat chat, boolean createdLocally) {
                    String from = chat.getParticipant().replace("@"+SERVER_NAME+"/"+RESOURCE,"");
                    if(!mChatMap.containsKey(from)){
                        chat.addMessageListener(mChatMessageListener);
                        mChatMap.put(from,chat);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        // FIXME: 2016/6/22
        // 配置各种Provider，如果不配置，则会无法解析数据
        //configureConnection(ProviderManager.getInstance());
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static XmppUtils getInstance() {
        if (mInstance == null) {
            synchronized (XmppUtils.class) {
                if (mInstance == null) {
                    mInstance = new XmppUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * 开启连接
     */
    public boolean openConnection() {
        if(mConnection!=null){
            try {
                mConnection.connect();
                mConnection.addConnectionListener(mConnectionListener);
                return  true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            // FIXME: 2016/6/22
            // 配置各种Provider，如果不配置，则会无法解析数据
            //configureConnection(ProviderManager.getInstance());
        }
        return false;
    }

    /**
     * 关闭连接
     */
    public void closeConnection() {
        if(mConnection!=null){
            //移除连接监听
            //connection.removeConnectionListener(connectionListener);
            if(mConnection.isConnected())
                mConnection.disconnect();
            mConnection = null;
        }
    }

    /**
     * 登录
     *
     * @param account
     *            登录帐号
     * @param password
     *            登录密码
     * @return
     */
    public boolean login(String account, String password) {
        try {
            if (mConnection == null)
                return false;
            mConnection.login(account, password, RESOURCE);
            // 更改在线状态
            Presence presence = new Presence(Presence.Type.available);
            mConnection.sendPacket(presence);
            // 添加连接监听
            //connectionListener = new TaxiConnectionListener();
            //getConnection().addConnectionListener(connectionListener);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 发送个人消息
     *
     * @param toAccount
     *            接收者账户
     * @param msg
     *            消息内容
     * @return
     */
    public boolean sendP2PChat(String toAccount, String msg) {
        try {
            if (mConnection == null)
                return false;
            Chat chat;
            if(mChatMap.containsKey(toAccount)){
                chat = mChatMap.get(toAccount);
            }else{
                ChatManager chatmanager = ChatManager.getInstanceFor(mConnection);
                //chat = chatmanager.createChat(toAccount + "@" + mConnection.getServiceName(), null);
                chat = chatmanager.createChat(toAccount + "@" + mConnection.getServiceName(), mChatMessageListener);
            }
            chat.sendMessage(msg);
            return true;
        } catch (SmackException.NotConnectedException e) {
            e.printStackTrace();
            return false;
        }
    }

    ChatMessageListener mChatMessageListener = new ChatMessageListener() {
        @Override
        public void processMessage(Chat chat, Message message) {
            Logger.d(chat.getParticipant() + ":" + message.getBody());
            //判断是否是离线消息并获取消息时间
            if(message.hasExtension("urn:xmpp:delay")){
                Logger.d(message.getExtension("urn:xmpp:delay").toXML().toString());
            }
        }
    };

    ConnectionListener mConnectionListener = new ConnectionListener() {
        @Override
        public void connected(XMPPConnection connection) {
            Logger.d("ConnectionListener:connected");
        }

        @Override
        public void authenticated(XMPPConnection connection, boolean resumed) {
            Logger.d("ConnectionListener:authenticated");
        }

        @Override
        public void connectionClosed() {
            Logger.d("ConnectionListener:connectionClosed");
        }

        @Override
        public void connectionClosedOnError(Exception e) {
            Logger.d("ConnectionListener:connectionClosedOnError");
        }

        @Override
        public void reconnectionSuccessful() {
            Logger.d("ConnectionListener:reconnectionSuccessful");
        }

        @Override
        public void reconnectingIn(int seconds) {
            Logger.d("ConnectionListener:reconnectingIn");
        }

        @Override
        public void reconnectionFailed(Exception e) {
            Logger.d("ConnectionListener:reconnectionFailed");
        }
    };
}
