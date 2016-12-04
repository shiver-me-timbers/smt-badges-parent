package shiver.me.timbers.badge;

import shiver.me.timbers.badge.data.SocialBadgeDataFactory;

/**
 * @author Karl Bennett
 */
public class SocialBadge extends CommonBadge<SocialBadgeOptions, SocialBadgeData> {

    static final String SOCIAL_TEMPLATE = "social-badge.mustache";

    public SocialBadge(String subject, String status, String subjectLink, String statusLink) {
        super(
            new SocialBadgeOptions(subject, status, subjectLink, statusLink),
            new SocialBadgeDataFactory(),
            SOCIAL_TEMPLATE
        );
    }
}
