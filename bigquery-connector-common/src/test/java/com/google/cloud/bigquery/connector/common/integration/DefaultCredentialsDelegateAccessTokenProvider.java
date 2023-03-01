/*
 * Copyright 2022 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.cloud.bigquery.connector.common.integration;

import static com.google.common.base.Preconditions.checkState;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.bigquery.connector.common.AccessToken;
import com.google.cloud.bigquery.connector.common.AccessTokenProvider;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Date;

/**
 * Basic implementation of AccessTokenProvider. This demonstrates a simple example of how
 * configuration can be passed to the AccessTokenProvider implementation. In this case the
 * configuration is simply treated as a token override in place of using {@link
 * GoogleCredentials#getApplicationDefault}. Token TTL is very small to allow refresh testing.
 */
public class DefaultCredentialsDelegateAccessTokenProvider implements AccessTokenProvider {

  private GoogleCredentials delegate;
  private String token;
  private int callCount = 0;

  public DefaultCredentialsDelegateAccessTokenProvider() {
    try {
      this.delegate = GoogleCredentials.getApplicationDefault();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public DefaultCredentialsDelegateAccessTokenProvider(String token) {
    this.token = token;
  }

  @Override
  public AccessToken getAccessToken() throws IOException {
    checkState(delegate != null || token != null);
    if (token != null) {
      return new AccessToken(token, new Date(System.currentTimeMillis() + 2000));
    } else {
      com.google.auth.oauth2.AccessToken accessToken = delegate.refreshAccessToken();
      callCount++;
      return new AccessToken(
          accessToken.getTokenValue(), new Date(System.currentTimeMillis() + 2000));
    }
  }

  int getCallCount() {
    return callCount;
  }
}
