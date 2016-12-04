package shiver.me.timbers.badge;

import shiver.me.timbers.badge.options.CommonBadgeOptions;

/**
 * @author Karl Bennett
 */
public class SocialBadgeOptions extends CommonBadgeOptions {

    private String subjectLink;
    private String statusLink;

    public SocialBadgeOptions(String subject, String status, String subjectLink, String statusLink) {
        super(subject, status);
        this.subjectLink = subjectLink;
        this.statusLink = statusLink;
    }

    public String getSubjectLink() {
        return subjectLink;
    }

    public String getStatusLink() {
        return statusLink;
    }
}
