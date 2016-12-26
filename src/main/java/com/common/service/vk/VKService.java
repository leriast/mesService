package com.common.service.vk;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.ServiceClientCredentialsFlowResponse;
import com.vk.api.sdk.objects.users.User;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import com.vk.api.sdk.queries.friends.FriendsSearchQuery;
import com.vk.api.sdk.queries.groups.GroupsGetMembersQuery;
import com.vk.api.sdk.queries.users.UsersGetQuery;

import java.io.IOException;

/*
* https://oauth.vk.com/authorize?client_id=5784490&scope=&redirect_uri=http://idealogic.lv&response_type=code
* */

/**
 * Created by root on 12/16/16.
 */
public class VKService {
public VKService(){
    Start();
}
    public  void Start(){
        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(transportClient);

//        ServiceClientCredentialsFlowResponse authResponse = null;
//        try {
//            authResponse = vk.oauth().serviceClientCredentialsFlow(5290648,"K6qPGMm9O4h96nzrtIr4")
//                    .execute();
//        } catch (ApiException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
//        GetResponse response = null;
//        ServiceActor actor = new ServiceActor(5784490,"xw3E81hqGLnY0KiUPlhH", authResponse.getAccessToken());

      //  authResponse=vk.oauth().userAuthorizationCodeFlow(5784490,"xw3E81hqGLnY0KiUPlhH","http://idealogic.lv",)


//        Actor ac=actor;
//        try {
//            response= vk.messages().get(ac).execute();
//            for(int i=0;i<response.getItems().size();i++){
//                System.out.println("pish "+response.getItems().get(i));
//            }
//        } catch (ApiException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }

//        String a=null;

//        try {
//            a=transportClient.post("https://api.vk.com/method/users.get?user_ids=sashka_ek&fields=bdate&v=5.60").getContent();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(a);
//        List<UserXtrCounters> uus=null;
//        try {
////            JSONObject obj = (JSONObject) new JSONParser().parse(a);
////            String id = String.valueOf(obj.get("id"));
//            //          uus=vk.users().get().userIds(id).execute();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

//            uus=vk.users().get().userIds("sashka_ek").execute();
//            for(UserXtrCounters u:uus){
//                System.out.println("2   "+u.getFirstName());
//            }

//        try {
////            a=transportClient.get("https://api.vk.com/method/messages.send?user_id=90256900&message=habrahabr&v=5.37&access_token="+authResponse.getAccessToken()).getContent();
////            System.out.println(a);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        try {

        //    ClientResponse result= (ClientResponse) transportClient.get("https://oauth.vk.com/authorize?client_id=5784490&display=page&redirect_uri=http://idealogic.lv&scope=friends&response_type=token&v=5.60&state=123456");

            //System.out.println(result.getContent().);

        //    String aa= String.valueOf(transportClient.get("https://oauth.vk.com/authorize?client_id=5784490&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends&response_type=token&v=5.60&state=123456"));
        //    System.out.println(aa);
       //     ClientResponse param=transportClient.post("https://oauth.vk.com/authorize?client_id=5784490&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends&response_type=token&v=5.60&state=123456");

//            for(String ad:aa.getHeaders().keySet()){
//                System.out.println("!   "+aa.getHeaders().get(ad));
//            }
//            System.out.println(aa.getContent());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
/*
*
https://oauth.vk.com/authorize?client_id=5784490&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends&response_type=token&v=5.60&state=123456

* */
//          29d04726bc2644fe60
//        try {
////            authResponse = vk.oauth().serviceClientCredentialsFlow(5784490,"xw3E81hqGLnY0KiUPlhH")
////                    .execute();
////            UserAuthResponse userAuthResponse = vk.oauth().userAuthorizationCodeFlow(5784490,"xw3E81hqGLnY0KiUPlhH","http://idealogic.lv","03e365335f6bc3744ef2e2c2345f665dba1e39ab1871b1ffc68bacf36227a6db6c4ce13715885f1bc35de").execute();//  transportClient.get("https://api.vk.com/method/messages.send?user_id=90256900&message=habrahabr&v=5.37&access_token=29d04726bc2644fe60");
////            System.out.println(vk.utils().resolveScreenName("habr").execute().getObjectId());;
//
//        } catch (ApiException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }

//        try {
////            //ClientResponse resp= transportClient.get("http://oauth.vk.com/authorize?client_id=5784490&scope=2&redirect_uri=http://oauth.vk.com/blank.html&display=wap&response_type=token");
////            for(String ad:resp.getHeaders().keySet()){
////                System.out.println(resp.getHeaders().get(ad));
////            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        ///         ed23c6694e3b98721df7f7bef3f5781d902eaa2dcd62d7da094bf915d133231ad4d7bfad94efeb07e1ecf



        ServiceClientCredentialsFlowResponse authResponse = null;

           // authResponse = vk.oauth().userAuthorizationCodeFlow(5290648,"K6qPGMm9O4h96nzrtIr4","ed23c6694e3b98721df7f7bef3f5781d902eaa2dcd62d7da094bf915d133231ad4d7bfad94efeb07e1ecf")
             //       .execute();

        try {
            authResponse = vk.oauth().serviceClientCredentialsFlow(5290648,"K6qPGMm9O4h96nzrtIr4")
                        .execute();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        UserActor actor =  actor = new UserActor( 90256900     /*,"K6qPGMm9O4h96nzrtIr4"*//*,"ed23c6694e3b98721df7f7bef3f5781d902eaa2dcd62d7da094bf915d133231ad4d7bfad94efeb07e1ecf"*/,"83f2928619131c6b8bdc45723d6ce2a86753c320dc6b780f2f7bf47747d83119040fd971d814b0383517b");
        FriendsSearchQuery user;
        try {
            GetResponse getResponse = vk.wall().get(actor)
                    .ownerId(1)
                    .count(100)
                    .offset(5)
                    //.filter("owner")
                    .execute();
            vk.messages().send(actor).userId(90256900).message("test").execute();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        try {
           // int o=vk.utils().resolveScreenName("habr").execute().getObjectId();

            GroupsGetMembersQuery a=vk.groups().getMembers().groupId("110589914");
            for(int i=0;i<a.execute().getItems().size();i++){
                int is=a.execute().getItems().get(i);
                if(is==90256900) {
                    UsersGetQuery user1 = vk.users().get().userIds(is + "");
                    User user2 = user1.execute().get(0);
                    vk.messages().send(actor).userId(90256900).message("test").execute();

                }
                System.out.println(is);
               String alaska=transportClient.post("https://api.vk.com/method/users.get?user_ids="+is+"&fields=bdate&v=5.60").getContent();
                System.out.println(alaska);
            }

//for(User use:UserGet.execute()){
//    System.out.println(use.getScreenName());
//}
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

























