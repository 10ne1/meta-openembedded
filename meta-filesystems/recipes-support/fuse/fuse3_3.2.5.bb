require fuse.inc

SRC_URI = "git://github.com/libfuse/libfuse \
	   file://fuse.conf \
"

SRCREV = "932f4190e2b65419cef9960e27a7f94fcab9c816"

LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c"

SRC_URI[md5sum] = "9bd4ce8184745fd3d000ca2692adacdb"
SRC_URI[sha256sum] = "832432d1ad4f833c20e13b57cf40ce5277a9d33e483205fc63c78111b3358874"

PACKAGES =+ "fuse-common"

FILES_fuse-common = "${sysconfdir} ${libdir}/udev"

INITSCRIPT_NAME = "fuse"
INITSCRIPT_PARAMS = "start 3 S . stop 20 0 6 ."

SYSTEMD_SERVICE_${PN} = ""

inherit meson pkgconfig update-rc.d systemd

do_install_append() {
    # by upstream libfuse design, v3 initscripts, udev rules and conf files
    # should be also used for v2 via the fuse-common package (they guarantee
    # backwards compatibility) so rename 'fuse3' -> 'fuse' for consistency
    mv ${D}${sysconfdir}/init.d/${PN} ${D}${sysconfdir}/init.d/fuse
    mv ${D}${base_libdir}/udev/rules.d/99-${PN}.rules ${D}${base_libdir}/udev/rules.d/99-fuse.rules

    # systemd class remove the sysv_initddir only if systemd_system_unitdir
    # contains anything, but it's not needed if sysvinit is not in DISTRO_FEATURES
    if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'false', 'true', d)}; then
        rm -rf ${D}${sysconfdir}/init.d/
    fi

    # Install systemd related configuration file
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}${sysconfdir}/modules-load.d
        install -m 0644 ${WORKDIR}/fuse.conf ${D}${sysconfdir}/modules-load.d
    fi
}
