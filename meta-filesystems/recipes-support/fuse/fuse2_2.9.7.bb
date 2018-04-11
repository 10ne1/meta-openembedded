require fuse.inc

SRC_URI = "git://github.com/libfuse/libfuse;branch=fuse-2_9_bugfix \
           file://gold-unversioned-symbol.patch \
           file://aarch64.patch \
           file://0001-fuse-fix-the-return-value-of-help-option.patch \
"

SRCREV = "df499bf1ce634f6e67d4d366c4475d32143f00f0"

LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c"

SRC_URI[md5sum] = "9bd4ce8184745fd3d000ca2692adacdb"
SRC_URI[sha256sum] = "832432d1ad4f833c20e13b57cf40ce5277a9d33e483205fc63c78111b3358874"

inherit autotools pkgconfig

do_configure_prepend() {
    export MOUNT_FUSE_PATH="${base_sbindir}"
    cp ${STAGING_DATADIR_NATIVE}/gettext/config.rpath ${S}
}

do_install_append() {
    # by upstream libfuse design, initscript, udev rules, conf and other files are
    # provided by fuse3 via a fuse-common package
    rm -r ${D}${sysconfdir}
}
