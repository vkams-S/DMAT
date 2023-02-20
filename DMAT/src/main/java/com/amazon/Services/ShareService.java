package com.amazon.Services;

import com.amazon.DB.ShareDAO;
import com.amazon.Model.Share;

public class ShareService {
    private ShareService(){}
    private static ShareService shareService = new ShareService();
    public static ShareService getInstance(){
        return shareService;
    }
    ShareDAO dao = ShareDAO.getInstance();
    public void displayAllShares()
    {
        for(Share s :dao.retrieve())
        {
            s.prettyPrint();
        }
    }

    public Share getShare(int shareId)
    {
        return (dao.retrieve("SELECT * FROM [Share] WHERE id="+shareId)).get(0);
    }
}
