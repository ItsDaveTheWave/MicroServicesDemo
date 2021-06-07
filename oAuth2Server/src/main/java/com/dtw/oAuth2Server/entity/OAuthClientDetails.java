package com.dtw.oAuth2Server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OAUTH_CLIENT_DETAILS")
@Data
@NoArgsConstructor
public class OAuthClientDetails {
	
	@Id
	@Column(name = "client_id", nullable = false)
	private String clientId;
	@Column(name = "client_secret", nullable = false)
	private String clientSecret;
	@Column(name = "web_server_redirect_uri")
	private String webServerRedirectUri;
	@Column(name = "scope")
	private String scope;
	@Column(name = "access_token_validity")
	private Integer accessTokenValidity; 
	@Column(name = "refresh_token_validity")
	private Integer refreshTokenValidity;
	@Column(name = "resource_ids")
	private String resourceIds;
	@Column(name = "authorized_grant_types")
	private String authorizedGrantTypes;
	@Column(name = "authorities")
	private String authorities;
	@Column(name = "additional_information")
	private String additionalInformation;
	@Column(name = "autoapprove")
	private String autoApprove;
}