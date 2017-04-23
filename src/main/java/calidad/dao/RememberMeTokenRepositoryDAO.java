package calidad.dao;

import java.util.Date;

import calidad.model.Token;

public interface RememberMeTokenRepositoryDAO 
{
	void createNewToken(Token token);
	void updateToken(String series, String tokenValue, Date lastUsed);
	Token getTokenForSeries(String seriesId);
	void removeUserTokens(String username);
}
