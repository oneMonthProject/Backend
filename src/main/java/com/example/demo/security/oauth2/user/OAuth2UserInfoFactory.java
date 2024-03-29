package com.example.demo.security.oauth2.user;

import com.example.demo.constant.OAuthProvider;

/**
 * OAuth2 인증 시 엑세스 토큰으로 사용자 정보를 가져왔을 때,
 * OAuth2 제공자 별로 OAuth2UserInfo 객체를 생성해주는 팩토리
 */
public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String oAuthProviderName,
                                                   String oAuthProviderId) {

        // 카카오
        if(OAuthProvider.KAKAO.getOAuthProviderName().equals(oAuthProviderName)) {
            return new KakaoOAuth2UserInfo(oAuthProviderId);
        }
        return null;
    }
}
