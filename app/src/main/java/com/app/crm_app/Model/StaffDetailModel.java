package com.app.crm_app.Model;

import java.util.List;

public class StaffDetailModel {

    /**
     * data : [{"firstname":"Shubham","staffid":"1","proposalid":"1","content":"tesy"},{"firstname":"Shubham","staffid":"1","proposalid":"2","content":"ghfbngfbhg"},{"firstname":"Shubham","staffid":"1","proposalid":"0","content":"gsfdsdsg"},{"firstname":"Shubham","staffid":"1","proposalid":"0","content":"gsfdsdsg"},{"firstname":"Shubham","staffid":"1","proposalid":"0","content":"gsfdsdsg"},{"firstname":"Shubham","staffid":"1","proposalid":"0","content":"gsfdsdsg"},{"firstname":"Shubham","staffid":"1","proposalid":"0","content":"gsfdsdsg"}]
     * message : Successfully
     * status : 1
     */


    private String message;
    private int status;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * firstname : Shubham
         * staffid : 1
         * proposalid : 1
         * content : tesy
         */

        private String firstname;
        private String staffid;
        private String proposalid;
        private String content;

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getStaffid() {
            return staffid;
        }

        public void setStaffid(String staffid) {
            this.staffid = staffid;
        }

        public String getProposalid() {
            return proposalid;
        }

        public void setProposalid(String proposalid) {
            this.proposalid = proposalid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
